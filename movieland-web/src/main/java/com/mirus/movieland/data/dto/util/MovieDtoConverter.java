package com.mirus.movieland.data.dto.util;

import com.mirus.movieland.data.dto.UpdateMovieDto;
import com.mirus.movieland.entity.Movie;

public class MovieDtoConverter {
    public static Movie convert(UpdateMovieDto movieDto) {
        Movie movie = new Movie();
        movie.setDescription(movieDto.getDescription());
        movie.setNameNative(movieDto.getNameNative());
        movie.setNameRussian(movieDto.getNameRussian());
        movie.setPicturePath(movieDto.getPicturePath());
        movie.setYearOfRelease(movieDto.getYearOfRelease());
        movie.setPrice(movieDto.getPrice());
        movie.setRating(movieDto.getRating());
        return movie;
    }
}
