package com.mirus.movieland.service;

import com.mirus.movieland.entity.Movie;
import com.mirus.movieland.repository.jdbc.SortParameters;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();

    List<Movie> findAll(SortParameters sortParameters);

    List<Movie> findRandom();

    List<Movie> findByGenreId(int genreId);

    List<Movie> findByGenreId(int genreId, SortParameters sortParameters);

    Movie findById(int id);
}
