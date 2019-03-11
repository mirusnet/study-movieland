package com.mirus.movieland.repository.jdbc.mapper;

import com.mirus.movieland.entity.Genre;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GenreRowMapperTest {

    @Test
    public void testRowMap() throws SQLException {
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("TheOne");

        GenreRowMapper genreRowMapper = new GenreRowMapper();
        Genre genre = genreRowMapper.mapRow(mockResultSet, 0);

        assertEquals(1, genre.getId());
        assertEquals("TheOne", genre.getName());
    }
}