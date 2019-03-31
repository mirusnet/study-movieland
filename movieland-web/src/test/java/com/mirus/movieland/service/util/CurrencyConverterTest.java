package com.mirus.movieland.service.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class CurrencyConverterTest {
    @Test
    public void testConvert() {
        double original = 90;
        double rate = 30;
        assertEquals(3, CurrencyConverter.convert(original, rate), 0.0001);
    }
}