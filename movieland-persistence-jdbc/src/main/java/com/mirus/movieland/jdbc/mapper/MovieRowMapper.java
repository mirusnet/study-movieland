package com.mirus.movieland.jdbc.mapper;

import com.mirus.movieland.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Movie movie = new Movie();
        movie.setId(resultSet.getInt("id"));
        movie.setNameNative(resultSet.getString("original_name"));
        movie.setNameRussian(resultSet.getString("russian_name"));
        movie.setDescription(resultSet.getString("description"));
        movie.setPicturePath(resultSet.getString("poster"));
        movie.setPrice(resultSet.getFloat("price"));
        movie.setRating(resultSet.getFloat("rating"));
        movie.setYearOfRelease(resultSet.getString("year"));
        return movie;
    }
}
