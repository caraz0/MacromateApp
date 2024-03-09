package com.android.macromate.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.macromate.R;
import com.android.macromate.data.dao.ItemListDAO;
import com.android.macromate.data.model.ItemListModel;
import com.android.macromate.data.model.ListWithItemModel;
import com.android.macromate.view.activities.ListItemsActivity;
import com.android.macromate.view.holders.ItemListAdapterHolder;

import java.util.List;

public class ListItemAdapter extends RecyclerView.Adapter<ItemListAdapterHolder> {

    private final ListItemsActivity activity;
    private final ItemListDAO itemListDAO;
    private List<ItemListModel> items;

    public ListItemAdapter(ListItemsActivity activity, ItemListDAO itemListDAO,
                           ListWithItemModel items) {
        this.activity = activity;
        this.itemListDAO = itemListDAO;
        this.items = itemListDAO.getByListId(items.getList().getListId());

        itemListDAO.getByListIdAsLiveData(items.getList().getListId())
                .observe(activity, listWithItemModels -> {
                    this.items = listWithItemModels;
                    this.notifyDataSetChanged();
                });
    }

    @NonNull
    @Override
    public ItemListAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_list_item, parent, false);

        return new ItemListAdapterHolder(view, itemListDAO);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListAdapterHolder holder, int position) {
        ItemListModel listModel = items.get(position);
        holder.bind(listModel);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public Context getContext() {
        return activity.getApplicationContext();
    }

    public FragmentManager getFragmentManager() {
        return activity.getSupportFragmentManager();
    }

    public void updateName(String s, int position) {
        var item = items.get(position);
        item.setName(s);

        itemListDAO.updateItemList(item);
        notifyItemChanged(position);
    }

    public void deleteItem(int position) {
        var item = items.get(position);
        itemListDAO.deleteItemList(item);

        notifyItemChanged(position);
    }

}
