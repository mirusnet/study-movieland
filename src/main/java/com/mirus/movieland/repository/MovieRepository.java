package com.mirus.movieland.repository;

import com.mirus.movieland.entity.Movie;
import com.mirus.movieland.repository.jdbc.SortParameters;

import java.util.List;

public interface MovieRepository {
    List<Movie> findAll();

    List<Movie> findAll(SortParameters sortParameters);

    List<Movie> findRandom(int count);

    List<Movie> findByGenreId(int genreId);

    List<Movie> findByGenreId(int genreId, SortParameters sortParameters);
}
