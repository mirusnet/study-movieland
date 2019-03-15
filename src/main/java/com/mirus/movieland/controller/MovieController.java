package com.mirus.movieland.controller;

import com.mirus.movieland.data.dto.MovieDto;
import com.mirus.movieland.entity.Movie;
import com.mirus.movieland.repository.jdbc.SortParameters;
import com.mirus.movieland.service.MovieService;
import com.mirus.movieland.service.util.MovieToMovieDtoConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final MovieToMovieDtoConverter movieToMovieDtoConverter;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<MovieDto> getAll(
            @RequestParam(value = "rating", required = false) String ratingSortOrder,
            @RequestParam(value = "price", required = false) String priceSortOrder) {

        Optional<SortParameters> sortParameters = buildSortParameters(ratingSortOrder, priceSortOrder);

        if (sortParameters.isPresent()) {
            return movieToMovieDtoConverter.convert(movieService.findAll(sortParameters.get()));
        }

        return movieToMovieDtoConverter.convert(movieService.findAll());
    }

    @GetMapping(path = "/random", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<MovieDto> getThreeRandom() {
        return movieToMovieDtoConverter.convert(movieService.findRandom());
    }

    @GetMapping(path = "/genre/{genreId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<MovieDto> getByGenreId(
            @PathVariable int genreId,
            @RequestParam(value = "rating", required = false) String ratingSortOrder,
            @RequestParam(value = "price", required = false) String priceSortOrder) {

        Optional<SortParameters> sortParameters = buildSortParameters(ratingSortOrder, priceSortOrder);

        if (sortParameters.isPresent()) {
            return movieToMovieDtoConverter.convert(movieService.findByGenreId(genreId, sortParameters.get()));
        }

        return movieToMovieDtoConverter.convert(movieService.findByGenreId(genreId));
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Movie getMovieById(@PathVariable int id) {
        return movieService.findById(id);
    }


    private static Optional<SortParameters> buildSortParameters(String ratingSortOrder, String priceSortOrder) {
        if (ratingSortOrder != null) {
            return Optional.of(new SortParameters("rating", SortParameters.SortDirection.fromValue(ratingSortOrder)));
        }

        if (priceSortOrder != null) {
            return Optional.of(new SortParameters("price", SortParameters.SortDirection.fromValue(priceSortOrder)));
        }

        return Optional.empty();
    }
}
