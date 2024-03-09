package com.android.macromate.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.android.macromate.data.model.ListModel;
import com.android.macromate.data.model.ListWithItemModel;
import com.android.macromate.data.model.MockProduct;

import java.util.List;

@Dao
public interface ListDAO {

    @Query("SELECT * FROM list ORDER BY listId DESC")
    List<ListWithItemModel> getAll();

    @Query("SELECT * FROM list ORDER BY listId DESC")
    LiveData<List<ListWithItemModel>> getAllAsLiveData();

    @Query("SELECT * FROM list WHERE listId = :id")
    ListWithItemModel getById(int id);

    @Insert
    void insert(ListModel listModel);

    @Update
    void updateList(ListModel listModel);

    @Delete
    void deleteList(ListModel listModel);
}
