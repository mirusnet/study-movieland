package com.mirus.movieland.repository.jdbc;

import com.mirus.movieland.entity.Movie;
import com.mirus.movieland.repository.MovieRepository;
import com.mirus.movieland.repository.jdbc.mapper.MovieRowMapper;
import com.mirus.movieland.repository.jdbc.util.QueryBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MovieRepositoryImpl implements MovieRepository {

    private static final MovieRowMapper MOVIE_ROW_MAPPER = new MovieRowMapper();
    private static final String GET_ALL_SQL = "select id, original_name, russian_name, description, poster, price, rating, year from  movie";
    private static final String GET_THREE_RANDOM = "select id, original_name, russian_name, description, poster, price, rating, year from  movie order by RAND() limit ?";
    private static final String GET_ALL_BY_GENRE_ID = "select m.id, m.original_name, m.russian_name, m.description, m.poster, m.price, m.rating, m.year from movie m join movie_genre mg on m.id = mg.movieid where mg.genreid = ?";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Movie> findAll() {
        return jdbcTemplate.query(GET_ALL_SQL, MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> findAll(SortParameters sortParameters) {
        return jdbcTemplate.query(QueryBuilder.addSortParameters(GET_ALL_SQL, sortParameters), MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> findRandom(int count) {
        return jdbcTemplate.query(GET_THREE_RANDOM, MOVIE_ROW_MAPPER, count);
    }

    @Override
    public List<Movie> findByGenreId(int genreId) {
        return jdbcTemplate.query(GET_ALL_BY_GENRE_ID, MOVIE_ROW_MAPPER, genreId);
    }

    @Override
    public List<Movie> findByGenreId(int genreId, SortParameters sortParameters) {
        return jdbcTemplate.query(QueryBuilder.addSortParameters(GET_ALL_BY_GENRE_ID, sortParameters), MOVIE_ROW_MAPPER, genreId);
    }
}