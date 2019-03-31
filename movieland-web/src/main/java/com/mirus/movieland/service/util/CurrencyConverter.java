package com.mirus.movieland.service.util;

public class CurrencyConverter {
    public static double convert(double original, double rate) {
        return Math.round(original / rate);
    }
}
