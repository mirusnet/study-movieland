package com.mirus.movieland.service.impl;

import com.mirus.movieland.entity.Currency;
import com.mirus.movieland.entity.Movie;
import com.mirus.movieland.repository.MovieRepository;
import com.mirus.movieland.repository.data.SortParameters;
import com.mirus.movieland.service.CurrencyService;
import com.mirus.movieland.service.MovieEnrichmentService;
import com.mirus.movieland.service.MovieService;
import com.mirus.movieland.service.util.CurrencyConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final CurrencyService currencyService;

    private MovieEnrichmentService parallelMovieEnrichmentService;
    private MovieEnrichmentService defaultMovieEnrichmentService;


    @Value("${movie.random.limit:3}")
    private int randomLimit;

    @Value("${movie.parallel.enrichment.enabled:false}")
    private boolean isParallelEnrichmentEnabled;

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
        if (isParallelEnrichmentEnabled) {
            parallelMovieEnrichmentService.enrich(movie);
        } else {
            defaultMovieEnrichmentService.enrich(movie);
        }
        return movie;
    }

    @Override
    public Movie findById(int id, Currency currency) {
        Movie movie = findById(id);
        Double rate = currencyService.getRateByCurrency(currency);
        movie.setPrice(CurrencyConverter.convert(movie.getPrice(), rate));
        return movie;
    }


    @Autowired
    @Qualifier("parallel")
    public void setParallelMovieEnrichmentService(MovieEnrichmentService parallelMovieEnrichmentService) {
        this.parallelMovieEnrichmentService = parallelMovieEnrichmentService;
    }

    @Autowired
    @Qualifier("default")
    public void setDefaultMovieEnrichmentService(MovieEnrichmentService defaultMovieEnrichmentService) {
        this.defaultMovieEnrichmentService = defaultMovieEnrichmentService;
    }
}
