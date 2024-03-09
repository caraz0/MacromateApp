package com.android.macromate.integration.mappers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Imapper<R> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public abstract R map(JsonNode json);

    protected String asTextOrElse(JsonNode node, String path) {
        return asTextOrElse(node, path, null);
    }

    protected String asTextOrElse(JsonNode node, String path, String elseStr) {
        return node.has(path)
                ? node.get(path).asText()
                : elseStr;
    }

    protected List<String> asListText(JsonNode node, String path) {
        return asListText(node, path, Collections.emptyList());
    }

    protected List<String> asListText(JsonNode node, String path, List<String> elseStr) {
        return node.has(path)
                ? parseList(node.get(path).asText())
                : elseStr;
    }

    private List<String> parseList(String s) {
        List<String> categories = new ArrayList<>();

        for (String s1 : s.split(","))
            categories.add(s1.strip());

        return categories;
    }

}
