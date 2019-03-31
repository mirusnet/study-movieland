package com.mirus.movieland.service.util;

import com.mirus.movieland.dto.MovieDto;
import com.mirus.movieland.entity.Movie;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieToMovieDtoConverterTest {
    private MovieToMovieDtoConverter movieToMovieDtoConverter = new MovieToMovieDtoConverterImpl();

    @Test
    public void convert() {
        Movie movie = new Movie();
        movie.setId(48);
        movie.setYearOfRelease("48");
        movie.setPicturePath("http://metalobaza.com/vsepodesiat");
        movie.setNameRussian("nameRussian");
        movie.setNameNative("nameNative");
        movie.setDescription("movieDescription");
        movie.setPrice(48.48);
        movie.setRating(49.49);

        MovieDto movieDto = movieToMovieDtoConverter.convert(movie);
        assertEquals(movie.getId(), movieDto.getId());
        assertEquals(movie.getYearOfRelease(), movieDto.getYearOfRelease());
        assertEquals(movie.getPicturePath(), movieDto.getPicturePath());
        assertEquals(movie.getNameRussian(), movieDto.getNameRussian());
        assertEquals(movie.getPrice(), movieDto.getPrice(), 0.0001);
        assertEquals(movie.getRating(), movieDto.getRating(), 0.0001);
    }
}