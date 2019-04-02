package com.mirus.movieland.service.util;

import com.mirus.movieland.dto.MovieDto;
import com.mirus.movieland.entity.Movie;

import java.util.List;

public interface MovieToMovieDtoConverter {
    MovieDto convert(Movie movie);

    List<MovieDto> convert(List<Movie> movies);
}
