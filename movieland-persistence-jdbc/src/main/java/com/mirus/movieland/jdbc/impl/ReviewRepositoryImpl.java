package com.mirus.movieland.jdbc.impl;

import com.mirus.movieland.entity.Review;
import com.mirus.movieland.repository.ReviewRepository;
import com.mirus.movieland.jdbc.mapper.ReviewRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepository {

    private static final ReviewRowMapper REVIEW_ROW_MAPPER = new ReviewRowMapper();

    private static final String GET_ALL_REVIEWS_BY_MOVIE_ID = "select" +
            " m.id as movie_id, u.id as user_id, u.name, u.email, r.id as review_id, r.text" +
            " from movie m join review r join user u on m.id = r.movieid and r.userid = u.id where m.id = ?";
    private static final String INSERT_REVIEW = "insert into review(text, userid, movieid) values(?, ?, ?)";

    private final JdbcTemplate jdbcTemplate;


    @Override
    public List<Review> findByMovieId(int id) {
        return jdbcTemplate.query(GET_ALL_REVIEWS_BY_MOVIE_ID, REVIEW_ROW_MAPPER, id);
    }

    @Override
    public void save(Review review) {
        jdbcTemplate.update(INSERT_REVIEW, review.getText(), review.getUser().getId(), review.getMovie().getId());
    }
}
