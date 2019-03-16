package com.mirus.movieland.service;

import com.mirus.movieland.entity.Review;

import java.util.List;

public interface ReviewService extends MovieEnrichable{
    List<Review> findByMovieId(int id);
}
