package com.android.macromate.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.android.macromate.data.model.ShopLocationModel;

import java.util.List;

@Dao
public interface ShopLocationDAO {

    @Query("SELECT * FROM ShopLocations")
    List<ShopLocationModel> getAllMockProducts();

    @Query("SELECT * FROM shoplocations WHERE id = :id")
    ShopLocationModel getMockProductById(int id);

    @Insert
    void insert(ShopLocationModel shopLocationModel);

    @Update
    void updateMockProduct(ShopLocationModel shopLocationModel);

    @Delete
    void deleteMockProduct(ShopLocationModel shopLocationModel);

}
