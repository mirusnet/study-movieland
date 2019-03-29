package com.mirus.movieland.jdbc.mapper;

import com.mirus.movieland.entity.Review;
import com.mirus.movieland.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewRowMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
        Review review = new Review();
        review.setId(rs.getInt("review_id"));
        review.setText(rs.getString("text"));

        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));

        review.setUser(user);
        return review;
    }
}
