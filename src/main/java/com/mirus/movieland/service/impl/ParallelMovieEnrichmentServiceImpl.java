package com.mirus.movieland.service.impl;

import com.mirus.movieland.entity.Country;
import com.mirus.movieland.entity.Genre;
import com.mirus.movieland.entity.Movie;
import com.mirus.movieland.entity.Review;
import com.mirus.movieland.service.CountryService;
import com.mirus.movieland.service.GenreService;
import com.mirus.movieland.service.MovieEnrichmentService;
import com.mirus.movieland.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service("parallel")
@RequiredArgsConstructor
public class ParallelMovieEnrichmentServiceImpl implements MovieEnrichmentService {

    private final ExecutorService executorService;
    private final GenreService genreService;
    private final CountryService countryService;
    private final ReviewService reviewService;

    private int enrichmentTimeoutSeconds;

    @Override
    public void enrich(final Movie movie) {

        Runnable genre = () -> {
            List<Genre> genres = genreService.findByMovieId(movie.getId());
            if (!Thread.currentThread().isInterrupted()) {
                movie.setGenres(genres);
            }
        };

        Runnable country = () -> {
            List<Country> countries = countryService.findByMovieId(movie.getId());
            if (!Thread.currentThread().isInterrupted()) {
                movie.setCountries(countries);
            }
        };

        Runnable review = () -> {
            List<Review> reviews = reviewService.findByMovieId(movie.getId());
            if (!Thread.currentThread().isInterrupted()) {
                movie.setReviews(reviews);
            }
        };

        List<Runnable> runnables = Arrays.asList(genre, country, review);
        List<Callable<Object>> callable = runnables.stream().map(Executors::callable).collect(Collectors.toList());

        try {
            log.info("Using parallel movie enrichment service");
            executorService.invokeAll(callable, enrichmentTimeoutSeconds, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.debug("Parallel Movie enrichment was interrupted ", e);
        }
    }


    @Value("${movie.parallel.enrichment.timeout}")
    public void setEnrichmentTimeoutSeconds(int enrichmentTimeoutSeconds) {
        this.enrichmentTimeoutSeconds = enrichmentTimeoutSeconds;
    }
}
