package com.android.macromate.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.android.macromate.data.dao.ItemListDAO;
import com.android.macromate.data.dao.ListDAO;
import com.android.macromate.data.dao.MockProductDao;
import com.android.macromate.data.dao.ShopLocationDAO;
import com.android.macromate.data.dao.TestDAO;
import com.android.macromate.data.model.ItemListModel;
import com.android.macromate.data.model.ListModel;
import com.android.macromate.data.model.MockProduct;
import com.android.macromate.data.model.ShopLocationModel;
import com.android.macromate.data.model.TestModel;

/**
 * Class necessary fro ORM to work. Just add the models to
 * the entities list and then create a new abstract method
 * that returns the corresponding DAO
 */
@Database(entities = {TestModel.class, MockProduct.class,
        ListModel.class, ItemListModel.class, ShopLocationModel.class}, version = 6)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract TestDAO getTestDAO();

    public abstract MockProductDao getMockProductDao();

    public abstract ShopLocationDAO getShopLocationDAO();

    public abstract ListDAO getListDAO();

    public abstract ItemListDAO getItemDAO();

    // volatile: "su valor puede ser cambiado por múltiples hilos de ejecución simultáneamente"
    // private static volatile AppDatabase INSTANCE;
}
