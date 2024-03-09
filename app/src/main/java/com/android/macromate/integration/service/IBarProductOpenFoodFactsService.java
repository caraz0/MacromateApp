package com.android.macromate.integration.service;

import com.android.macromate.core.Languages;
import com.android.macromate.dtos.integration.OpenFoodFactsProductDTO;

public interface IBarProductOpenFoodFactsService {

    default OpenFoodFactsProductDTO searchByBarCode(String barcode) {
        return searchByBarCode(barcode, Languages.getDefault());
    }

    OpenFoodFactsProductDTO searchByBarCode(String barcode, Languages language);

}
