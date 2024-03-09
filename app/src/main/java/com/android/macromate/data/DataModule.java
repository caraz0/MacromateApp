package com.android.macromate.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.android.macromate.data.dao.ItemListDAO;
import com.android.macromate.data.dao.ListDAO;
import com.android.macromate.data.dao.MockProductDao;
import com.android.macromate.data.dao.ShopLocationDAO;
import com.android.macromate.data.dao.TestDAO;
import com.android.macromate.data.repositories.MockProductRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

/**
 * Class to handle all data dependency injection related sources.
 * <p>
 * Here we define the {@link AppDatabase} class needed for room ORM to work,
 * and also it is
 */
@Module
@InstallIn(SingletonComponent.class)
public class DataModule {

    @Provides
    @Singleton
    AppDatabase appDatabase(@ApplicationContext Context ctx) {
        return Room.databaseBuilder(ctx, AppDatabase.class, "database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                //.createFromAsset("database/data.db")
                .build();
    }

    @Provides
    @Singleton
    TestDAO testDAO(AppDatabase appDatabase) {
        return appDatabase.getTestDAO();
    }

    @Provides
    @Singleton
    MockProductDao mockProductDao(AppDatabase appDatabase) {
        return appDatabase.getMockProductDao();
    }

    @Provides
    @Singleton
    ShopLocationDAO shopLocationDAO(AppDatabase appDatabase) {
        return appDatabase.getShopLocationDAO();
    }

    @Provides
    @Singleton
    MockProductRepository mockProductRepository(MockProductDao mockProductDao,
                                                ExecutorService executorService
    ) {
        return new MockProductRepository(mockProductDao, executorService);
    }

    @Provides
    @Singleton
    ListDAO listDao(AppDatabase appDatabase) {
        return appDatabase.getListDAO();
    }

    @Provides
    @Singleton
    ItemListDAO itemDao(AppDatabase appDatabase) {
        return appDatabase.getItemDAO();
    }

    /**
     * Creates a new single thread executor, that ensures
     * that all tasks passed are executed in order
     */

    @Provides
    @Singleton
    ExecutorService executorService() {
        return Executors.newSingleThreadExecutor();
    }


}
