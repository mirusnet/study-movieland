package com.mirus.movieland.service.impl;

import com.mirus.movieland.entity.Country;
import com.mirus.movieland.entity.Genre;
import com.mirus.movieland.entity.Movie;
import com.mirus.movieland.entity.Review;
import com.mirus.movieland.repository.CountryRepository;
import com.mirus.movieland.repository.GenreRepository;
import com.mirus.movieland.repository.MovieRepository;
import com.mirus.movieland.repository.ReviewRepository;
import com.mirus.movieland.repository.jdbc.SortParameters;
import com.mirus.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final CountryRepository countryRepository;
    private final GenreRepository genreRepository;
    private final ReviewRepository reviewRepository;

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

    @Override
    public Movie findById(int id) {
        Movie movie = movieRepository.findById(id);
        movie.setCountries(countryRepository.findByMovieId(id));
        movie.setGenres(genreRepository.findByMovieId(id));
        movie.setReviews(reviewRepository.findByMovieId(id));
        return movie;
    }
}
