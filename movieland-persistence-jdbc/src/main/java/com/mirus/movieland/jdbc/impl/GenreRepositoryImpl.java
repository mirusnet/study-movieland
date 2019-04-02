package com.mirus.movieland.jdbc.impl;

import com.mirus.movieland.entity.Genre;
import com.mirus.movieland.repository.GenreRepository;
import com.mirus.movieland.jdbc.mapper.GenreRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
    private static final String DELINK_MOVIE_GENRE_SQL = "delete from movie_genre where movieid = ?";
    private static final String LINK_MOVIE_GENRE_SQL = "insert into movie_genre(movieid, genreid) values (?, ?)";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Genre> findAll() {
        return jdbcTemplate.query(GET_ALL_GENRES_SQL, GENRE_ROW_MAPPER);
    }

    @Override
    public List<Genre> findByMovieId(int id) {
        return jdbcTemplate.query(GET_ALL_GENRES_BY_MOVIE_ID, GENRE_ROW_MAPPER, id);
    }

    @Override
    public void deleteMappingByMovieId(int movieId) {
        jdbcTemplate.update(DELINK_MOVIE_GENRE_SQL, movieId);
    }

    @Override
    public void insertMappingByMovieId(int movieId, int[] genreIds) {
        jdbcTemplate.batchUpdate(LINK_MOVIE_GENRE_SQL, new BatchPreparedStatementSetter() {

            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, movieId);
                ps.setInt(2, genreIds[i]);
            }

            public int getBatchSize() {
                return genreIds.length;
            }
        });
    }
}
