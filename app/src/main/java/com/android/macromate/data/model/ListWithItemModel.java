package com.android.macromate.data.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.List;

public class ListWithItemModel implements Serializable {

    @Embedded public ListModel list;
    @Relation(parentColumn = "listId", entityColumn = "listId")
    public List<ItemListModel> items;

    public ListModel getList() {
        return list;
    }

    public List<ItemListModel> getItems() {
        return items;
    }




}
