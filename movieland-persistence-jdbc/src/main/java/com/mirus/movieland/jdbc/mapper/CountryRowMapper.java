package com.mirus.movieland.jdbc.mapper;

import com.mirus.movieland.entity.Country;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryRowMapper implements RowMapper<Country> {
    @Override
    public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Country(rs.getInt("id"), rs.getString("name"));
    }
}
