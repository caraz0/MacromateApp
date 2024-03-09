package com.android.macromate.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity(tableName = "list")
public class ListModel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int listId;

    @NonNull
    private String name;

    @NonNull
    private Date createdAt;

    public ListModel(int listId, @NonNull String name, @NonNull Date createdAt) {
        this.listId = listId;
        this.name = name;
        this.createdAt = createdAt;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@NonNull Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListModel listModel = (ListModel) o;
        return listId == listModel.listId && name.equals(listModel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listId, name);
    }

    @Override
    public String toString() {
        return "ListModel{" +
                "id=" + listId +
                ", Name='" + name + '\'' +
                '}';
    }
}
