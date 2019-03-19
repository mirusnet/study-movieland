package com.mirus.movieland.service;

import com.mirus.movieland.entity.Movie;

public interface MovieEnrichable {
    void enrich(Movie movie);
}
