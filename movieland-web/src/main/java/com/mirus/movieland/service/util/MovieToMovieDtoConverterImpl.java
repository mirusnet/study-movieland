package com.mirus.movieland.service.util;

import com.mirus.movieland.data.dto.MovieDto;
import com.mirus.movieland.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieToMovieDtoConverterImpl implements MovieToMovieDtoConverter {

    @Override
    public MovieDto convert(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setNameNative(movie.getNameNative());
        movieDto.setNameRussian(movie.getNameRussian());
        movieDto.setPicturePath(movie.getPicturePath());
        movieDto.setPrice(movie.getPrice());
        movieDto.setRating(movie.getRating());
        movieDto.setYearOfRelease(movie.getYearOfRelease());
        return movieDto;
    }

    @Override
    public List<MovieDto> convert(List<Movie> movies) {
        return movies.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
