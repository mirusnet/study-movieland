package com.mirus.movieland.jdbc.impl;

import com.mirus.movieland.data.SortParameters;
import com.mirus.movieland.entity.Movie;
import com.mirus.movieland.repository.MovieRepository;
import com.mirus.movieland.jdbc.mapper.MovieRowMapper;
import com.mirus.movieland.jdbc.util.QueryBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MovieRepositoryImpl implements MovieRepository {

    private static final MovieRowMapper MOVIE_ROW_MAPPER = new MovieRowMapper();
    private static final String GET_ALL_SQL = "select id, original_name, russian_name, description, poster, price, rating, year from  movie";
    private static final String GET_THREE_RANDOM = "select id, original_name, russian_name, description, poster, price, rating, year from  movie order by RAND() limit ?";
    private static final String GET_ALL_BY_GENRE_ID = "select m.id, m.original_name, m.russian_name, m.description, m.poster, m.price, m.rating, m.year from movie m join movie_genre mg on m.id = mg.movieid where mg.genreid = ?";
    private static final String GET_ONE_BY_ID = "select id, original_name, russian_name, description, poster, price, rating, year from  movie where id = ?";
    private static final String INSERT_SQL = "insert into movie(original_name, russian_name, description, poster, price, rating, year) values(:original_name, :russian_name, :description, :poster, :price, :rating, :year)";
    private static final String UPDATE_SQL = "update movie set original_name=:original_name, russian_name=:russian_name, description=:description, poster=:poster, price=:price, rating=:rating, year=:year where id=:id";
    private static final String PARTIAL_UPDATE_SQL = "update movie set original_name=:original_name, russian_name=:russian_name, poster=:poster where id=:id";

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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

    @Override
    public Movie findById(int id) {
        return jdbcTemplate.queryForObject(GET_ONE_BY_ID, MOVIE_ROW_MAPPER, id);
    }

    @Override
    public Movie save(Movie movie) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("original_name", movie.getNameNative())
                .addValue("russian_name", movie.getNameRussian())
                .addValue("year", movie.getYearOfRelease())
                .addValue("description", movie.getDescription())
                .addValue("poster", movie.getPicturePath())
                .addValue("rating", movie.getRating()) //I see that there is no rating in trello :)
                .addValue("price", movie.getPrice());

        if (movie.getId() == 0) {
            namedParameterJdbcTemplate.update(INSERT_SQL, mapSqlParameterSource, keyHolder);
            movie.setId(keyHolder.getKey().intValue());
        } else {
            mapSqlParameterSource.addValue("id", movie.getId());
            namedParameterJdbcTemplate.update(UPDATE_SQL, mapSqlParameterSource, keyHolder);
        }

        return movie;
    }

    @Override
    public Movie update(Movie movie) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("original_name", movie.getNameNative())
                .addValue("russian_name", movie.getNameRussian())
                .addValue("poster", movie.getPicturePath())
                .addValue("id", movie.getId());

        namedParameterJdbcTemplate.update(PARTIAL_UPDATE_SQL, mapSqlParameterSource);

        return movie;
    }
}

