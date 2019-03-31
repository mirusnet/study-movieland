package com.mirus.movieland.jdbc.mapper;

import com.mirus.movieland.entity.Review;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReviewRowMapperTest {

    @Test
    public void testRowMap() throws SQLException {
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockResultSet.getInt("review_id")).thenReturn(1);
        when(mockResultSet.getString("text")).thenReturn("Boring film ever seen");
        when(mockResultSet.getInt("user_id")).thenReturn(48);
        when(mockResultSet.getString("name")).thenReturn("donVito");
        when(mockResultSet.getString("email")).thenReturn("vito@carleone");

        ReviewRowMapper reviewRowMapper = new ReviewRowMapper();
        Review review = reviewRowMapper.mapRow(mockResultSet, 0);

        assertEquals(1, review.getId());
        assertEquals("Boring film ever seen", review.getText());
        assertEquals(48, review.getUser().getId());
        assertEquals("donVito", review.getUser().getName());
        assertEquals("vito@carleone", review.getUser().getEmail());
    }
}

