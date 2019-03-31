package com.mirus.movieland.jdbc.mapper;

import com.mirus.movieland.entity.Country;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CountryRowMapperTest {

    @Test
    public void testRowMap() throws SQLException {
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("Canada");

        CountryRowMapper countryRowMapper = new CountryRowMapper();

        Country country = countryRowMapper.mapRow(mockResultSet, 0);

        assertEquals(1, country.getId());
        assertEquals("Canada", country.getName());
    }
}