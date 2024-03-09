package com.android.macromate.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "test")
public class TestModel {

    public TestModel() {
    }

    public TestModel(String barcode) {
        this.barcode = barcode;
    }

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    public String barcode;

    @Override
    public String toString() {
        return "TestModel{" +
                "id=" + id +
                ", barcode='" + barcode + '\'' +
                '}';
    }

}
