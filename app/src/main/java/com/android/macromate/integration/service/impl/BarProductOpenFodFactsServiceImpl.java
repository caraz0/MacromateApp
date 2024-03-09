

package com.android.macromate.integration.service.impl;

import com.android.macromate.core.Languages;
import com.android.macromate.dtos.integration.OpenFoodFactsProductDTO;
import com.android.macromate.integration.adapters.IBarProductOpenFoodFactsAdapter;
import com.android.macromate.integration.service.IBarProductOpenFoodFactsService;

import java.io.IOException;

import javax.inject.Inject;

public class BarProductOpenFodFactsServiceImpl implements IBarProductOpenFoodFactsService {

    private final IBarProductOpenFoodFactsAdapter barProductOpenFoodFactsAdapter;

    @Inject
    public BarProductOpenFodFactsServiceImpl(IBarProductOpenFoodFactsAdapter barProductOpenFoodFactsAdapter) {
        this.barProductOpenFoodFactsAdapter = barProductOpenFoodFactsAdapter;
    }

    @Override
    public OpenFoodFactsProductDTO searchByBarCode(String barcode, Languages language) {
        try {
            return barProductOpenFoodFactsAdapter.searchByBarCode(language.getAlphaCode(), barcode)
                    .execute().body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
