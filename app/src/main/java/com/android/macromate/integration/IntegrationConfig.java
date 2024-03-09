package com.android.macromate.integration;

import com.android.macromate.dtos.integration.OpenFoodFactsProductDTO;
import com.android.macromate.integration.adapters.IBarProductOpenFoodFactsAdapter;
import com.android.macromate.integration.adapters.ILocationsApiAdapter;
import com.android.macromate.integration.mappers.OpenFoodFactsProductConverter;
import com.android.macromate.integration.service.IBarProductOpenFoodFactsService;
import com.android.macromate.integration.service.ILocationService;
import com.android.macromate.integration.service.impl.BarProductOpenFodFactsServiceImpl;
import com.android.macromate.integration.service.impl.LocationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Module to register beans for hilt dependency injector
 */
@Module
@InstallIn(SingletonComponent.class)
public class IntegrationConfig {

    /**
     * Instance of jackson ObjectMapper. We also register here all
     * the serializer/deserializers
     */
    @Provides
    @Singleton
    ObjectMapper objectMapper(OpenFoodFactsProductConverter converter) {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(OpenFoodFactsProductDTO.class, converter);

        return new ObjectMapper()
                .registerModule(module);
    }

    @Provides
    @Singleton
    Retrofit retrofit(
            ObjectMapper objectMapper
    ) {
        return new Retrofit.Builder()
                .baseUrl("http://localhost/")
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
    }

    @Provides
    @Singleton
    IBarProductOpenFoodFactsAdapter barProductOpenFoodFactsAdapter(Retrofit retrofit) {
        return retrofit.create(IBarProductOpenFoodFactsAdapter.class);
    }

    @Provides
    @Singleton
    ILocationsApiAdapter locationsApiAdapter(Retrofit retrofit) {
        return retrofit.create(ILocationsApiAdapter.class);
    }

    @Module
    @InstallIn(SingletonComponent.class)
    abstract class IBarProductServiceModule {

        @Binds
        abstract IBarProductOpenFoodFactsService barProductOpenFoodFactsService(
                BarProductOpenFodFactsServiceImpl barProductOpenFodFactsServiceImpl
        );

        @Binds
        abstract ILocationService locationService(
            LocationServiceImpl locationServiceImpl
        );

    }

}
