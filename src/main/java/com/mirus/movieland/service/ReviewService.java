package com.mirus.movieland.service;

import com.mirus.movieland.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findByMovieId(int id);
}
