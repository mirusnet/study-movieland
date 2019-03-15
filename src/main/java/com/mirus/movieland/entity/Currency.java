package com.mirus.movieland.entity;

import java.util.EnumSet;

public enum Currency {
    EUR, USD;

    public static Currency fromValue(String value) {
        return EnumSet.allOf(Currency.class)
                .stream()
                .filter(v -> v.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown sort order: " + value));
    }
}
