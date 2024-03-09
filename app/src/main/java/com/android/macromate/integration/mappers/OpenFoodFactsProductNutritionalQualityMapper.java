package com.android.macromate.integration.mappers;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class OpenFoodFactsProductNutritionalQualityMapper extends Imapper<Map<String, String>> {

    @Inject
    public OpenFoodFactsProductNutritionalQualityMapper() {
    }

    @Override
    public Map<String, String> map(JsonNode json) {
        var innerJson = json.get("nutrient_levels");

        return extractFieldsAndValues(innerJson);
    }

    @NonNull
    private HashMap<String, String> extractFieldsAndValues(JsonNode innerJson) {
        var nutritionMap = new HashMap<String, String>();

        for (var iterator = innerJson.fieldNames(); iterator.hasNext(); ) {
            var next = iterator.next();
            nutritionMap.put(next, innerJson.get(next).asText());
        }

        return nutritionMap;
    }

}
