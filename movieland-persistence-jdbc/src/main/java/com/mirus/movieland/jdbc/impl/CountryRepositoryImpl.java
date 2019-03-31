package com.mirus.movieland.jdbc.impl;

import com.mirus.movieland.entity.Country;
import com.mirus.movieland.jdbc.mapper.CountryRowMapper;
import com.mirus.movieland.repository.CountryRepository;
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
public class CountryRepositoryImpl implements CountryRepository {

    private static final CountryRowMapper COUNTRY_ROW_MAPPER = new CountryRowMapper();

    private static final String GET_ALL_BY_MOVIE_ID = "select c.id, c.name from movie m" +
            " join movie_country mc join country c on m.id = mc.movieid and mc.countryid = c.id" +
            " where m.id = ? order by c.name;";

    private static final String GET_ALL = "select id, name from country";
    private static final String DELINK_MOVIE_COUNTRY_SQL = "delete from movie_country where movieid=?";
    private static final String LINK_MOVIE_COUNTRY_SQL = "insert into movie_country(movieid, countryid) values(?,?)";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Country> findByMovieId(int id) {
        return jdbcTemplate.query(GET_ALL_BY_MOVIE_ID, COUNTRY_ROW_MAPPER, id);
    }

    @Override
    public List<Country> findAll() {
        return jdbcTemplate.query(GET_ALL, COUNTRY_ROW_MAPPER);
    }

    @Override
    public void deleteMappingByMovieId(int movieId) {
        jdbcTemplate.update(DELINK_MOVIE_COUNTRY_SQL, movieId);
    }

    @Override
    public void insertMappingByMovieId(int movieId, int[] countryIds) {
        jdbcTemplate.batchUpdate(LINK_MOVIE_COUNTRY_SQL, new BatchPreparedStatementSetter() {

            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, movieId);
                ps.setInt(2, countryIds[i]);
            }

            public int getBatchSize() {
                return countryIds.length;
            }
        });
    }
}
