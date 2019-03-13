package com.mirus.movieland.service.impl;

import com.mirus.movieland.entity.Movie;
import com.mirus.movieland.repository.MovieRepository;
import com.mirus.movieland.repository.jdbc.SortParameters;
import com.mirus.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Value("${movie.random.limit:3}")
    private int randomLimit;

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> findAll(SortParameters sortParameters) {
        return movieRepository.findAll(sortParameters);
    }

    @Override
    public List<Movie> findRandom() {
        return movieRepository.findRandom(randomLimit);
    }

    @Override
    public List<Movie> findByGenreId(int genreId) {
        return movieRepository.findByGenreId(genreId);

    }

    @Override
    public List<Movie> findByGenreId(int genreId, SortParameters sortParameters) {
        return movieRepository.findByGenreId(genreId, sortParameters);
    }
}
