package com.mirus.movieland.repository.jdbc.impl;

import com.mirus.movieland.entity.User;
import com.mirus.movieland.repository.UserRepository;
import com.mirus.movieland.repository.jdbc.mapper.UserRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private static final UserRowMapper USER_ROM_MAPPER = new UserRowMapper();
    private static final String GET_USER_BY_ID_SQL = "select id, name, email, password from user where email = ?";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            User user = jdbcTemplate.queryForObject(GET_USER_BY_ID_SQL, USER_ROM_MAPPER, email);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            log.debug("User with email " + email + " not found");
            return Optional.empty();
        }
    }
}
