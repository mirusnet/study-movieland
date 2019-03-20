package com.mirus.movieland.repository.jdbc;

import static org.junit.Assert.*;

import com.mirus.movieland.repository.data.SortParameters;
import org.junit.Test;

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