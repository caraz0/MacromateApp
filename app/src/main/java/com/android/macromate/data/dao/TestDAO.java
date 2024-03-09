package com.android.macromate.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.android.macromate.data.model.TestModel;

import java.util.List;

@Dao
public interface TestDAO {

    @Query("SELECT * FROM test")
    List<TestModel> getAll();

    @Insert
    void add(TestModel test);

}
