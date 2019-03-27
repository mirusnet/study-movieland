package com.mirus.movieland.service.impl;

import com.mirus.movieland.entity.Currency;
import com.mirus.movieland.entity.Movie;
import com.mirus.movieland.service.MovieEnrichmentService;
import com.mirus.movieland.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CachedMovieServiceImplTest {

    @Mock
    private MovieService movieService;

    @Mock
    private MovieEnrichmentService movieEnrichmentService;

    private CachedMovieServiceImpl cachedMovieService;

    private List<Movie> movies;

    private Movie movie;

    @Before
    public void setup() {
        cachedMovieService = new CachedMovieServiceImpl(movieService, movieEnrichmentService);

        Movie movie_0 = new Movie();
        movie_0.setNameNative("NATIVE_1");

        Movie movie_1 = new Movie();
        movie_1.setNameNative("NATIVE_2");

        movie = new Movie();
        movie.setId(48);
        movie.setNameNative("NATIVE_48");
        movie.setPrice(10);

        movies = Arrays.asList(movie_0, movie_1);
    }

    @Test
    public void testFindAll() {
        when(movieService.findAll()).thenReturn(movies);

        assertEquals(2, cachedMovieService.findAll().size());
        assertEquals("NATIVE_1", cachedMovieService.findAll().get(0).getNameNative());
        assertEquals("NATIVE_2", cachedMovieService.findAll().get(1).getNameNative());
    }

    @Test
    public void testFindRandom() {
        when(movieService.findRandom()).thenReturn(movies);

        assertEquals(2, cachedMovieService.findRandom().size());
        assertEquals("NATIVE_1", cachedMovieService.findRandom().get(0).getNameNative());
        assertEquals("NATIVE_2", cachedMovieService.findRandom().get(1).getNameNative());
    }

    @Test
    public void testFindById() {
        when(movieService.findById(48)).thenReturn(movie);

        assertEquals(48, cachedMovieService.findById(48).getId());
        assertEquals("NATIVE_48", cachedMovieService.findById(48).getNameNative());
    }

    @Test
    public void testFindByIdWithCurrency() {
        when(movieService.findById(48, Currency.USD)).thenReturn(movie);
        assertEquals(10, cachedMovieService.findById(48, Currency.USD).getPrice(), 0.1);
    }

    @Test
    public void testUpdate() {
        int[] genres = {1, 2, 3};
        int[] countries = {1, 2, 3};

        when(movieService.findById(48)).thenReturn(movie);

        assertEquals(48, cachedMovieService.findById(48).getId());
        assertEquals("NATIVE_48", cachedMovieService.findById(48).getNameNative());

        movie.setNameNative("CHANGED_NAME_48");
        assertEquals("CHANGED_NAME_48", cachedMovieService.update(movie, genres, countries).getNameNative());
    }
}