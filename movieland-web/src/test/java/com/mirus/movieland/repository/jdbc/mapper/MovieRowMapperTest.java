package com.mirus.movieland.repository.jdbc.mapper;

import com.mirus.movieland.entity.Movie;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieRowMapperTest {
    @Test
    public void testRowMap() throws SQLException {
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("original_name")).thenReturn("originalName");
        when(mockResultSet.getString("russian_name")).thenReturn("russianName");
        when(mockResultSet.getString("poster")).thenReturn("posterHttpPath");
        when(mockResultSet.getFloat("price")).thenReturn(48.48F);
        when(mockResultSet.getFloat("rating")).thenReturn(49.49F);
        when(mockResultSet.getString("year")).thenReturn("1986");

        MovieRowMapper movieRowMapper = new MovieRowMapper();
        Movie movie = movieRowMapper.mapRow(mockResultSet, 0);

        assertEquals(1, movie.getId());
        assertEquals("originalName", movie.getNameNative());
        assertEquals("russianName", movie.getNameRussian());
        assertEquals("posterHttpPath", movie.getPicturePath());
        assertEquals(48.48, movie.getPrice(), 0.0001);
        assertEquals(49.49, movie.getRating(), 0.0001);
        assertEquals("1986", movie.getYearOfRelease());

    }
}