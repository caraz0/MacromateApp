package com.android.macromate.integration.service.impl;

import com.android.macromate.dtos.integration.LocationDTO;
import com.android.macromate.integration.adapters.ILocationsApiAdapter;
import com.android.macromate.integration.service.ILocationService;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

public class LocationServiceImpl implements ILocationService {

    private final ILocationsApiAdapter locationsApiAdapter;

    @Inject
    public LocationServiceImpl(ILocationsApiAdapter locationsApiAdapter) {
        this.locationsApiAdapter = locationsApiAdapter;
    }

    @Override
    public List<LocationDTO> getAllLocations() {
        try {
            return locationsApiAdapter.requestAllLocations().execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
