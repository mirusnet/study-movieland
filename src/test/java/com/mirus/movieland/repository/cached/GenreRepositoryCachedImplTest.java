package com.mirus.movieland.repository.cached;

import com.mirus.movieland.entity.Genre;
import com.mirus.movieland.repository.GenreRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GenreRepositoryCachedImplTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreRepositoryCachedImpl genreRepositoryCached = new GenreRepositoryCachedImpl();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCachedGenres() {
        Genre genre = new Genre(1, "FirstGenre");

        Genre genre1 = new Genre(2, "SecondGenre");

        List<Genre> genres;

        when(genreRepository.findAll()).thenReturn(Arrays.asList(genre));
        genreRepositoryCached.refresh();

        genres = genreRepositoryCached.findAll();
        assertEquals(1, genres.size());

        when(genreRepository.findAll()).thenReturn(Arrays.asList(genre, genre1));
        genres = genreRepositoryCached.findAll();
        assertEquals(1, genres.size());

        genreRepositoryCached.refresh();
        genres = genreRepositoryCached.findAll();
        assertEquals(2, genres.size());

        verify(genreRepository, times(2)).findAll();
    }
}