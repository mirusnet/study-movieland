package com.mirus.movieland.controller;

import com.mirus.movieland.data.dto.MovieDto;
import com.mirus.movieland.service.MovieService;
import com.mirus.movieland.service.util.MovieToMovieDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final MovieToMovieDtoConverter movieToMovieDtoConverter;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<MovieDto> getAll() {
        return movieToMovieDtoConverter.convert(movieService.findAll());
    }

    @GetMapping(path = "/random", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<MovieDto> getThreeRandom() {
        return movieToMovieDtoConverter.convert(movieService.findRandom());
    }

    @GetMapping(path = "/genre/{genreId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<MovieDto> getByGenreId(@PathVariable int genreId) {
        return movieToMovieDtoConverter.convert(movieService.findByGenreId(genreId));
    }
}
