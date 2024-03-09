package com.android.macromate.view.holders;

import android.view.View;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.macromate.R;
import com.android.macromate.data.dao.ItemListDAO;
import com.android.macromate.data.model.ItemListModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

public class ItemListAdapterHolder extends RecyclerView.ViewHolder{

    private final ItemListDAO itemListDAO;

    public ItemListAdapterHolder(@NonNull View itemView, ItemListDAO itemListDAO) {
        super(itemView);
        this.itemListDAO = itemListDAO;
    }

    public void bind(ItemListModel listModel) {
        CheckBox item = super.itemView.findViewById(R.id.todoCheckBox);
        item.setChecked(listModel.isSelected());
        item.setText(listModel.getName());
        item.setOnCheckedChangeListener((compoundButton, b) -> {
            listModel.setSelected(b);
            itemListDAO.updateItemList(listModel);
        });
    }

}
