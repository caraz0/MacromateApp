package com.android.macromate.core.mappers.impl;

import com.android.macromate.core.mappers.IOpenFoodDtoToProductModelMapper;
import com.android.macromate.data.model.MockProduct;
import com.android.macromate.dtos.integration.OpenFoodFactsProductDTO;

import java.util.Date;

public class OpenFoodDtoToProductModelMapperImpl implements IOpenFoodDtoToProductModelMapper {

    @Override
    public MockProduct map(OpenFoodFactsProductDTO dto) {
        return MockProduct.builder()
                .withProductName(dto.getName())
                .withImageUrl(dto.getImageUrl())
                .withBarcode(dto.getBarcode())
                .withBrands(dto.getBrands().isEmpty() ? "" : dto.getBrands().get(0))
                .withNutritionalScore(dto.getNutritionalScore())
                .withQuantity(dto.getQuantity())
                .withCategories(dto.getCategories())
                .withNutritionalInfo(dto.getNutritionalInfo())
                .withIngredients(dto.getIngredients())
                .withNutrientLevels(dto.getNutrientLevels())
                .withScannedDate(new Date())
                .build();
    }

}
