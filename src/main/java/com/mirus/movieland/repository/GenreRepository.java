package com.mirus.movieland.repository;

import com.mirus.movieland.entity.Genre;

import java.util.List;

public interface GenreRepository {
    List<Genre> findAll();

    List<Genre> findByMovieId(int id);
}
