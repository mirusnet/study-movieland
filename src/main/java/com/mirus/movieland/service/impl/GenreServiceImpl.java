package com.mirus.movieland.service.impl;

import com.mirus.movieland.entity.Genre;
import com.mirus.movieland.entity.Movie;
import com.mirus.movieland.repository.GenreRepository;
import com.mirus.movieland.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public List<Genre> findByMovieId(int id) {
        return genreRepository.findByMovieId(id);
    }

    @Override
    public void enrich(Movie movie) {
        movie.setGenres(findByMovieId(movie.getId()));
    }
}
