package com.android.macromate.core;

public enum Languages {
    SPANISH("es"),
    ENGLISH("en");

    private final String alphaCode;

    Languages(String alphaCode) {
        this.alphaCode = alphaCode;
    }

    public String getAlphaCode() {
        return alphaCode;
    }

    public static Languages getDefault() {
        return SPANISH;
    }

    @Override
    public String toString() {
        return this.alphaCode;
    }
}
