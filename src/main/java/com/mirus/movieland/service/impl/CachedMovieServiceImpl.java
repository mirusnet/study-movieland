package com.mirus.movieland.service.impl;

import com.mirus.movieland.entity.Currency;
import com.mirus.movieland.entity.Movie;
import com.mirus.movieland.repository.data.SortParameters;
import com.mirus.movieland.service.MovieEnrichmentService;
import com.mirus.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.lang.ref.SoftReference;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class CachedMovieServiceImpl implements MovieService {
    private final MovieService movieService;
    private final MovieEnrichmentService movieEnrichmentService;
    private final Map<Integer, SoftReference<Movie>> movieCache = new ConcurrentHashMap<>();

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
        SoftReference<Movie> movieSoftReference = movieCache.compute(id, (Integer k, SoftReference<Movie> v) -> {
            if (v != null && v.get() != null) {
                log.info("Movie for id {} was taken from the cache", id);
                return v;
            } else {
                log.info("Adding movie {} to the cache", id);
                return new SoftReference<>(movieService.findById(id));
            }
        });

        return movieSoftReference.get();
    }

    @Override
    public Movie findById(int id, Currency currency) {
        return movieService.findById(id, currency);
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
        Movie updatedMovie = movieService.update(movie, genreIds, contryIds);
        movieEnrichmentService.enrich(updatedMovie);
        SoftReference<Movie> softReference = movieCache.computeIfPresent(movie.getId(), (key, oldRef) -> new SoftReference<>(movie));
        return (softReference == null) ? new Movie(updatedMovie) : new Movie(softReference.get());
    }
}
