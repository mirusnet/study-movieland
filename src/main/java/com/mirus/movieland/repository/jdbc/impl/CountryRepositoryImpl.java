package com.mirus.movieland.repository.jdbc.impl;

import com.mirus.movieland.entity.Country;
import com.mirus.movieland.repository.CountryRepository;
import com.mirus.movieland.repository.jdbc.mapper.CountryRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CountryRepositoryImpl implements CountryRepository {

    private static final CountryRowMapper COUNTRY_ROW_MAPPER = new CountryRowMapper();

    private static final String GET_ALL_BY_MOVIE_ID = "select c.id, c.name from movie m" +
            " join movie_country mc join country c on m.id = mc.movieid and mc.countryid = c.id" +
            " where m.id = ? order by c.name;";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Country> findByMovieId(int id) {
        return jdbcTemplate.query(GET_ALL_BY_MOVIE_ID, COUNTRY_ROW_MAPPER, id);
    }
}
