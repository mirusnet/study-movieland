package com.mirus.movieland.repository.jdbc.mapper;

import com.mirus.movieland.data.Role;
import com.mirus.movieland.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setEmail(resultSet.getString("email"));
        user.setName(resultSet.getString("name"));
        user.setRole(Role.valueOf(resultSet.getString("role")));
        user.setPassword(resultSet.getString("password"));
        return user;
    }
}
