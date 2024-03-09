package com.android.macromate.integration.adapters;

import com.android.macromate.core.Languages;
import com.android.macromate.dtos.integration.OpenFoodFactsProductDTO;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IBarProductOpenFoodFactsAdapter {

    @GET("https://{lang}.openfoodfacts.org/api/v2/search")
    Call<OpenFoodFactsProductDTO> searchByBarCode(@Path("lang") String language,
                                                 @Query("code") String barcode);

}
