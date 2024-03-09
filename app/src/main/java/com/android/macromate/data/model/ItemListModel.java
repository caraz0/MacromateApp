package com.android.macromate.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "item_list",
        foreignKeys = @ForeignKey(entity = ListModel.class,
        parentColumns = "listId",
        childColumns = "listId",
        onDelete = ForeignKey.CASCADE))
public class ItemListModel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int itemId;

    @NonNull
    private int listId;

    @NonNull
    private String name;

    private boolean isSelected;

    public ItemListModel(int itemId, int listId, @NonNull String name, boolean isSelected) {
        this.itemId = itemId;
        this.listId = listId;
        this.name = name;
        this.isSelected = isSelected;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemListModel that = (ItemListModel) o;
        return itemId == that.itemId && listId == that.listId && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, listId, name);
    }

    @Override
    public String toString() {
        return "ItemListModel{" +
                "itemId=" + itemId +
                ", listId=" + listId +
                ", name='" + name + '\'' +
                '}';
    }
}
