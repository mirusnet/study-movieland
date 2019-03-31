package com.mirus.movieland.data;

import org.junit.Test;

import static org.junit.Assert.*;

public class SortParametersTest {

    @Test(expected = IllegalArgumentException.class)
    public void testUnsupported() {
        SortParameters.SortDirection.fromValue("BubbleSort");
    }

    @Test
    public void testAsc() {
        assertSame(SortParameters.SortDirection.ASC, SortParameters.SortDirection.fromValue("aSc"));
    }

    @Test
    public void testDesc() {
        assertSame(SortParameters.SortDirection.DESC, SortParameters.SortDirection.fromValue("dEsc"));
    }
}