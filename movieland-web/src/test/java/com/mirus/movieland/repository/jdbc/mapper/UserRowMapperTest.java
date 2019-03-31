package com.mirus.movieland.repository.jdbc.mapper;

import com.mirus.movieland.data.Role;
import com.mirus.movieland.entity.User;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserRowMapperTest {
    @Test
    public void testRowMap() throws SQLException {
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("UserName");
        when(mockResultSet.getString("role")).thenReturn("ADMIN");
        when(mockResultSet.getString("password")).thenReturn("password");

        UserRowMapper userRowMapper = new UserRowMapper();
        User user = userRowMapper.mapRow(mockResultSet, 0);

        assertEquals(1, user.getId());
        assertEquals("UserName", user.getName());
        assertEquals(Role.ADMIN, user.getRole());
        assertEquals("password", user.getPassword());
    }
}
