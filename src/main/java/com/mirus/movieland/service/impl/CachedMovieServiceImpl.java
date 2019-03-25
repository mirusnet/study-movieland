package com.mirus.movieland.service.impl;

import com.mirus.movieland.entity.Currency;
import com.mirus.movieland.entity.Movie;
import com.mirus.movieland.repository.data.SortParameters;
import com.mirus.movieland.service.CurrencyService;
import com.mirus.movieland.service.MovieEnrichmentService;
import com.mirus.movieland.service.MovieService;
import com.mirus.movieland.service.util.CurrencyConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.lang.ref.SoftReference;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Primary
@RequiredArgsConstructor
public class CachedMovieServiceImpl implements MovieService {
    private final MovieService movieService;
    private final CurrencyService currencyService;
    private MovieEnrichmentService movieEnrichmentService;

    private Map<Integer, SoftReference<Movie>> movieCache = new ConcurrentHashMap<>();

    @Override
    public List<Movie> findAll() {
        return movieService.findAll();
    }

    @Override
    public List<Movie> findAll(SortParameters sortParameters) {
        return movieService.findAll(sortParameters);
    }

    @Override
    public List<Movie> findRandom() {
        return movieService.findRandom();
    }

    @Override
    public List<Movie> findByGenreId(int genreId) {
        return movieService.findByGenreId(genreId);
    }

    @Override
    public List<Movie> findByGenreId(int genreId, SortParameters sortParameters) {
        return movieService.findByGenreId(genreId, sortParameters);
    }

    @Override
    public Movie findById(int id) {
        return movieCache.computeIfAbsent(id, (key) -> new SoftReference<>(movieService.findById(key))).get();
    }

    @Override
    public Movie findById(int id, Currency currency) {
        Movie movie = movieService.findById(id);
        Double rate = currencyService.getRateByCurrency(currency);
        movie.setPrice(CurrencyConverter.convert(movie.getPrice(), rate));
        return movie;
    }

    @Override
    public Movie save(Movie movie) {
        return movieService.save(movie);
    }

    @Override
    public Movie save(Movie movie, int[] genreIds, int[] contryIds) {
        return movieService.save(movie, genreIds, contryIds);
    }

    @Override
    public Movie update(Movie movie, int[] genreIds, int[] contryIds) {
        movieService.update(movie, genreIds, contryIds);
        movieEnrichmentService.enrich(movie);
        return movieCache.computeIfPresent(movie.getId(), (key, oldRef) -> new SoftReference<>(movie)).get();
    }

    @Autowired
    @Qualifier("default")
    public void setMovieEnrichmentService(MovieEnrichmentService movieEnrichmentService) {
        this.movieEnrichmentService = movieEnrichmentService;
    }
}
