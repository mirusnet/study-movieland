package com.mirus.movieland.repository.jdbc;

import com.mirus.movieland.entity.Genre;
import com.mirus.movieland.repository.GenreRepository;
import com.mirus.movieland.repository.jdbc.mapper.GenreRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class GenreRepositoryImpl implements GenreRepository {
    private static final GenreRowMapper GENRE_ROW_MAPPER = new GenreRowMapper();
    private static final String GET_ALL_GENRES_SQL = "select id, name from genre";

    private static final String GET_ALL_GENRES_BY_MOVIE_ID = "select g.id, g.name" +
            " from movie m join movie_genre mg join genre g" +
            " on m.id = mg.movieid and mg.genreid = g.id where m.id = ? order by g.name";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Genre> findAll() {
        return jdbcTemplate.query(GET_ALL_GENRES_SQL, GENRE_ROW_MAPPER);
    }

    @Override
    public List<Genre> findByMovieId(int id) {
        return jdbcTemplate.query(GET_ALL_GENRES_BY_MOVIE_ID, GENRE_ROW_MAPPER, id);
    }
}
