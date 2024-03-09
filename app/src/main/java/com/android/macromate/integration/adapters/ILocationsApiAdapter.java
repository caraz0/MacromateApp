package com.android.macromate.integration.adapters;

import com.android.macromate.dtos.integration.LocationDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ILocationsApiAdapter {

    @GET("https://macromate.divios.me/locations/all")
    @Headers({
            "Authorization: Basic bWFjcm9tYXRlOmZNWHVJZTQwTlVBdWFtZU45WUU2TVR3ekQ5TEhBcEJh"
    })
    Call<List<LocationDTO>> requestAllLocations();

}
