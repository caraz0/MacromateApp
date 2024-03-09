package com.android.macromate.view.models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.macromate.data.model.MockProduct;
import com.android.macromate.data.repositories.MockProductRepository;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

// Tremendamente boilerplate
public class MockProductViewModel extends AndroidViewModel {

    @Inject
    MockProductRepository repository;
    private LiveData<List<MockProduct>> allMockProducts;

    public MockProductViewModel(Application application) {
        super(application);
    }

    public LiveData<List<MockProduct>> getAllMockProducts() {
        if (Objects.isNull(allMockProducts))
            allMockProducts = repository.getAllMockProducts();

        return allMockProducts;
    }

    public void insert(MockProduct product) {
        repository.insert(product);
    }

}
