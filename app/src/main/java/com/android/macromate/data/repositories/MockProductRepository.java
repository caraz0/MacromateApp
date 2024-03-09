package com.android.macromate.data.repositories;

import androidx.lifecycle.LiveData;

import com.android.macromate.data.dao.MockProductDao;
import com.android.macromate.data.model.MockProduct;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

public class MockProductRepository {

    private final MockProductDao mockProductDao;

    private final ExecutorService executor;

    private LiveData<List<MockProduct>> allMockProducts;

    @Inject
    public MockProductRepository(MockProductDao mockProductDao,
                                 ExecutorService executor) {
        this.mockProductDao = mockProductDao;
        this.executor = executor;
    }

    public LiveData<List<MockProduct>> getAllMockProducts() {
        if (Objects.isNull(allMockProducts))
            allMockProducts = mockProductDao.getAllMockProducts();

        return allMockProducts;
    }

    public void insert(MockProduct product) {
        executor.submit(() -> mockProductDao.insert(product));
    }

}
