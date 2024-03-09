package com.android.macromate.integration.mappers;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

public class OpenFoodFactsProductNutritionalInfoMapper extends Imapper<Map<String, String>> {

    @Inject
    public OpenFoodFactsProductNutritionalInfoMapper() {
    }

    @Override
    public Map<String, String> map(JsonNode json) {
        var innerJson = json.get("nutriments");

        var nutritionValuesSet = getAllNutrimentsValues(innerJson);
        return extractNutrimentsValueAndUnit(innerJson, nutritionValuesSet);
    }

    private HashMap<String, String> extractNutrimentsValueAndUnit(JsonNode innerJson, HashSet<String> nutritionValuesSet) {
        var nutritionMap = new HashMap<String, String>();

        for (String s : nutritionValuesSet) {
            var value = innerJson.get(s + "_value");
            var unit = innerJson.get(s + "_unit");

            if (Objects.isNull(value) || Objects.isNull(unit))
                continue;

            nutritionMap.put(s, value.asText() + unit.asText());
        }

        return nutritionMap;
    }

    @NonNull
    private HashSet<String> getAllNutrimentsValues(JsonNode innerJson) {
        var nutritionValuesSet = new HashSet<String>();
        for (var iterator = innerJson.fieldNames(); iterator.hasNext(); ) {
            var next = iterator.next().split("_");
            if (next.length != 2) continue;

            nutritionValuesSet.add(next[0]);
        }
        return nutritionValuesSet;
    }

}
