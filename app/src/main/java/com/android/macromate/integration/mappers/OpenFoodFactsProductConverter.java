package com.android.macromate.integration.mappers;

import com.android.macromate.dtos.integration.OpenFoodFactsProductDTO;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

import javax.inject.Inject;

public class OpenFoodFactsProductConverter extends StdDeserializer<OpenFoodFactsProductDTO> {

    OpenFoodFactsProductMapper mapper;

    @Inject
    public OpenFoodFactsProductConverter(OpenFoodFactsProductMapper mapper) {
        this((Class<?>) null);
        this.mapper = mapper;
    }

    protected OpenFoodFactsProductConverter(Class<?> vc) {
        super(vc);
    }

    @Override
    public OpenFoodFactsProductDTO deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        return mapper.map(node);
    }
}
