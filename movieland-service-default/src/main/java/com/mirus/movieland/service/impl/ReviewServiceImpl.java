package com.mirus.movieland.service.impl;

import com.mirus.movieland.entity.Movie;
import com.mirus.movieland.entity.Review;
import com.mirus.movieland.repository.ReviewRepository;
import com.mirus.movieland.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public List<Review> findByMovieId(int id) {
        return reviewRepository.findByMovieId(id);
    }

    @Override
    public void enrich(Movie movie) {
        movie.setReviews(findByMovieId(movie.getId()));
    }

    @Override
    public void save(Review review) {
        reviewRepository.save(review);
    }
}
