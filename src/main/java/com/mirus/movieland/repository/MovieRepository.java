package com.mirus.movieland.repository;

import com.mirus.movieland.entity.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> findAll();

    List<Movie> findRandom(int count);

    List<Movie> findByGenreId(int genreId);
}
