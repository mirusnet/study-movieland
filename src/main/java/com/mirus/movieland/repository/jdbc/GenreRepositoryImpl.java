package com.mirus.movieland.repository.jdbc;

import com.mirus.movieland.entity.Genre;
import com.mirus.movieland.repository.GenreRepository;
import com.mirus.movieland.repository.jdbc.mapper.GenreRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class GenreRepositoryImpl implements GenreRepository {
    private static final GenreRowMapper GENRE_ROW_MAPPER = new GenreRowMapper();
    private static final String GET_ALL_GENRES_SQL = "select id, name from genre";

    private final JdbcTemplate jdbcTemplate;

    @Override
    @Cacheable("genres")
    public List<Genre> findAll() {
        return jdbcTemplate.query(GET_ALL_GENRES_SQL, GENRE_ROW_MAPPER);
    }
}
