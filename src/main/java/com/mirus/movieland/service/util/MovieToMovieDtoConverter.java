package com.mirus.movieland.service.util;

import com.mirus.movieland.data.dto.MovieDto;
import com.mirus.movieland.entity.Movie;

import java.util.List;

public interface MovieToMovieDtoConverter {
    List<MovieDto> convert(List<Movie> movies);
}
