package com.android.macromate.integration.mappers;

import com.android.macromate.dtos.integration.OpenFoodFactsProductDTO;
import com.fasterxml.jackson.databind.JsonNode;

import javax.inject.Inject;

/**
 * Class that handles the conversion from json to {@link OpenFoodFactsProductDTO}.
 * <p>
 * It also delegates some attributes mappings to inner mappers.
 */
public class OpenFoodFactsProductMapper extends Imapper<OpenFoodFactsProductDTO> {

    private final OpenFoodFactsProductNutritionalInfoMapper infoMapper;
    private final OpenFoodFactsProductNutritionalQualityMapper qualityMapper;

    @Inject
    public OpenFoodFactsProductMapper(
            OpenFoodFactsProductNutritionalInfoMapper infoMapper,
            OpenFoodFactsProductNutritionalQualityMapper qualityMapper
    ) {
        this.infoMapper = infoMapper;
        this.qualityMapper = qualityMapper;
    }

    @Override
    public OpenFoodFactsProductDTO map(JsonNode json) {
        JsonNode innerJson = json.get("products").get(0);  // Not sure why is wrapped inside an array

        return OpenFoodFactsProductDTO.builder()
                .withName(asTextOrElse(innerJson, "product_name", "NaN"))
                .withImageUrl(asTextOrElse(innerJson, "image_url", ""))
                .withBarcode(innerJson.get("code").asText())
                .withBrands(asListText(innerJson, "brands"))
                .withCategories(asListText(innerJson, "categories"))
                .withIngredients(asListText(innerJson, "ingredients_text"))
                .withStores(asListText(innerJson, "stories"))
                .withQuantity(asTextOrElse(innerJson, "quantity", "NaN"))
                .withNutritionalScore(asTextOrElse(innerJson, "nutriscore_grade", "Unknown"))
                .withNutritionalQuality(qualityMapper.map(innerJson))
                .withNutritionalInfo(infoMapper.map(innerJson))
                .build();
    }

}
