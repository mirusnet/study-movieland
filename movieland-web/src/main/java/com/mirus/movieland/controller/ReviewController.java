package com.mirus.movieland.controller;

import com.mirus.movieland.data.dto.ReviewDto;
import com.mirus.movieland.entity.Movie;
import com.mirus.movieland.entity.Review;
import com.mirus.movieland.entity.User;
import com.mirus.movieland.security.annotation.access.Secured;
import com.mirus.movieland.security.data.Role;
import com.mirus.movieland.service.MovieService;
import com.mirus.movieland.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final MovieService movieService;

    @Secured(Role.USER)
    @PostMapping(path = "/review", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void saveReview(@SessionAttribute("user") User user, @RequestBody ReviewDto reviewDto) {
        Movie movie = new Movie();
        movie.setId(reviewDto.getMovieId());

        Review review = new Review();
        review.setUser(user);
        review.setMovie(movie);
        review.setText(reviewDto.getText());

        reviewService.save(review);
    }
}
