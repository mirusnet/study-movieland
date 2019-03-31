package com.mirus.movieland.service;

import com.mirus.movieland.data.SortParameters;
import com.mirus.movieland.entity.Currency;
import com.mirus.movieland.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();

    List<Movie> findAll(SortParameters sortParameters);

    List<Movie> findRandom();

    List<Movie> findByGenreId(int genreId);

    List<Movie> findByGenreId(int genreId, SortParameters sortParameters);

    Movie findById(int id);

    Movie findById(int id, Currency currency);

    Movie save(Movie movie);

    Movie save(Movie movie, int[] genreIds, int[] contryIds);

    Movie update(Movie movie, int[] genreIds, int[] contryIds);
}
