package com.mirus.movieland.repository;

import com.mirus.movieland.data.SortParameters;
import com.mirus.movieland.entity.Movie;

import java.util.List;

public interface MovieRepository {
    List<Movie> findAll();

    List<Movie> findAll(SortParameters sortParameters);

    List<Movie> findRandom(int count);

    List<Movie> findByGenreId(int genreId);

    List<Movie> findByGenreId(int genreId, SortParameters sortParameters);

    Movie findById(int id);

    Movie save(Movie movie);

    Movie update(Movie movie);
}
