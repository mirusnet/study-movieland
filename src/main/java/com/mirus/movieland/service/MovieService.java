package com.mirus.movieland.service;

import com.mirus.movieland.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();

    List<Movie> findRandom();

    List<Movie> findByGenreId(int genreId);
}
