package com.android.macromate.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.android.macromate.data.model.ItemListModel;
import com.android.macromate.data.model.ListModel;

import java.util.List;

@Dao
public interface ItemListDAO {

    @Query("SELECT * FROM item_list ORDER BY itemId DESC")
    LiveData<List<ItemListModel>> getAll();

    @Query("SELECT * FROM item_list WHERE itemId = :id")
    ItemListModel getById(int id);

    @Query("SELECT * FROM item_list WHERE listId = :listId")
    List<ItemListModel> getByListId(int listId);

    @Query("SELECT * FROM item_list WHERE listId = :listId")
    LiveData<List<ItemListModel>> getByListIdAsLiveData(int listId);

    @Insert
    void insert(ItemListModel itemList);

    @Update
    void updateItemList(ItemListModel itemList);

    @Delete
    void deleteItemList(ItemListModel itemList);
}
