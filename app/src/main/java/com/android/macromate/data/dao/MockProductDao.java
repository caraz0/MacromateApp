package com.android.macromate.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.android.macromate.data.model.MockProduct;

import java.util.List;

@Dao
public interface MockProductDao {

    @Query("SELECT * FROM mock_products ORDER BY scanned_date DESC")
    LiveData<List<MockProduct>> getAllMockProducts();

    @Query("SELECT * FROM mock_products WHERE id = :id")
    MockProduct getMockProductById(int id);

    @Insert
    void insert(MockProduct mockProduct);

    @Update
    void updateMockProduct(MockProduct mockProduct);

    @Delete
    void deleteMockProduct(MockProduct mockProduct);

}

