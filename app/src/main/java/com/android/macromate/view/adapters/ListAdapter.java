package com.android.macromate.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.android.macromate.R;
import com.android.macromate.data.dao.ListDAO;
import com.android.macromate.data.model.ListWithItemModel;
import com.android.macromate.view.holders.ListAdapterHolder;
import com.android.macromate.view.nav_tabs.ListsPageFragment;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapterHolder> {

    private final ListsPageFragment pageFragment;
    private final ListDAO listDAO;

    private List<ListWithItemModel> items;

    public ListAdapter(ListsPageFragment pageFragment, ListDAO listDAO) {
        this.pageFragment = pageFragment;
        this.listDAO = listDAO;
        this.items = listDAO.getAll();

        listDAO.getAllAsLiveData().observe(pageFragment.getViewLifecycleOwner(), listWithItemModels -> {
          this.items = listWithItemModels;
          notifyDataSetChanged();
        });
    }

    @NonNull
    @Override
    public ListAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_list, parent, false);

        return new ListAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapterHolder holder, int position) {
        ListWithItemModel listModel = items.get(position);
        holder.bind(listModel, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void deleteItem(int position) {
        var item = items.get(position);
        listDAO.deleteList(item.list);
    }

    public void updateName(String name, int position) {
        var item = items.get(position).getList();
        item.setName(name);
        listDAO.updateList(item);
    }

    public Context getContext() {
        return pageFragment.getContext();
    }

}
