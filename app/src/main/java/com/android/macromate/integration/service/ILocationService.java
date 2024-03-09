package com.android.macromate.integration.service;

import com.android.macromate.dtos.integration.LocationDTO;

import java.util.List;

public interface ILocationService {

    List<LocationDTO> getAllLocations();

}
