package com.android.macromate.core;

import com.android.macromate.core.mappers.IOpenFoodDtoToProductModelMapper;
import com.android.macromate.core.mappers.impl.OpenFoodDtoToProductModelMapperImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class ProjectConfiguration {

    @Singleton
    @Provides
    public IOpenFoodDtoToProductModelMapper openFoodDtoToProductModelMapper() {
        return new OpenFoodDtoToProductModelMapperImpl();
    }

}
