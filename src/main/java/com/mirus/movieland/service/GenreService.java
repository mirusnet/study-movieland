package com.mirus.movieland.service;

import com.mirus.movieland.entity.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();

    List<Genre> findByMovieId(int id);
}
