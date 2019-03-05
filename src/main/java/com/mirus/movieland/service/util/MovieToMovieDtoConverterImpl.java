package com.mirus.movieland.service.util;

import com.mirus.movieland.data.dto.MovieDto;
import com.mirus.movieland.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieToMovieDtoConverterImpl implements MovieToMovieDtoConverter {
    @Override
    public List<MovieDto> convert(List<Movie> movies) {
        return movies.stream()
                .map(MovieDto::new)
                .collect(Collectors.toList());
    }
}
