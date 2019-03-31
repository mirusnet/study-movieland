package com.mirus.movieland.service;

import com.mirus.movieland.entity.Genre;

import java.util.List;

public interface GenreService extends MovieEnrichable{
    List<Genre> findAll();

    List<Genre> findByMovieId(int id);

    void deleteMappingByMovieId(int movieId);

    void insertMappingByMovieId(int movieId, int[] genreIds);
}
