package com.mirus.movieland.repository.jdbc.mapper;

import com.mirus.movieland.entity.CurrencyRate;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CurrencyRateRowMapperTest {
    @Test
    public void testRowMap() throws SQLException {
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getDouble("rate")).thenReturn(27.0);

        CurrencyRateRowMapper currencyRateRowMapper = new CurrencyRateRowMapper();

        CurrencyRate currencyRate = currencyRateRowMapper.mapRow(mockResultSet, 0);

        assertEquals(1, currencyRate.getId());
        assertEquals(27.0, currencyRate.getRate(), 0.00001);
    }
}