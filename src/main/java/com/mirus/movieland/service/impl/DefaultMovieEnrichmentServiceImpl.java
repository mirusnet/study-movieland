package com.mirus.movieland.service.impl;

import com.mirus.movieland.entity.Movie;
import com.mirus.movieland.service.CountryService;
import com.mirus.movieland.service.GenreService;
import com.mirus.movieland.service.MovieEnrichmentService;
import com.mirus.movieland.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("default")
@RequiredArgsConstructor
public class DefaultMovieEnrichmentServiceImpl implements MovieEnrichmentService {

    private final GenreService genreService;
    private final CountryService countryService;
    private final ReviewService reviewService;

    @Override
    public void enrich(Movie movie) {
        log.info("Using default movie enrichment service");
        countryService.enrich(movie);
        genreService.enrich(movie);
        reviewService.enrich(movie);
    }
}
