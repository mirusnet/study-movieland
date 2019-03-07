package com.mirus.movieland.repository.jdbc;

import com.mirus.movieland.entity.Movie;
import com.mirus.movieland.repository.MovieRepository;
import com.mirus.movieland.repository.jdbc.mapper.MovieRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MovieRepositoryImpl implements MovieRepository {

    private static final MovieRowMapper MOVIE_ROW_MAPPER = new MovieRowMapper();
    private static final String GET_ALL_SQL = "select id, original_name, russian_name, description, poster, price, raiting, year from  movie";
    private static final String GET_THREE_RANDOM = "select id, original_name, russian_name, description, poster, price, raiting, year from  movie order by RAND() limit ?";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Movie> findAll() {
        return jdbcTemplate.query(GET_ALL_SQL, MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> findRandom(int count) {
        return jdbcTemplate.query(GET_THREE_RANDOM, MOVIE_ROW_MAPPER, count);
    }
}