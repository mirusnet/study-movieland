package com.mirus.movieland.service;

import com.mirus.movieland.entity.Movie;

public interface ParallelMovieEnrichmentService {
    void enrich(Movie movie);
}
