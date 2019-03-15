package com.mirus.movieland.repository;

import com.mirus.movieland.entity.Review;

import java.util.List;

public interface ReviewRepository {
    List<Review> findByMovieId(int id);
}
