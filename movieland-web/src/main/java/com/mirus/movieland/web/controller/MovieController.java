package com.mirus.movieland.web.controller;

import com.mirus.movieland.data.SortParameters;
import com.mirus.movieland.dto.MovieDto;
import com.mirus.movieland.dto.UpdateMovieDto;
import com.mirus.movieland.entity.Currency;
import com.mirus.movieland.entity.Movie;
import com.mirus.movieland.entity.User;
import com.mirus.movieland.service.util.MovieDtoConverter;
import com.mirus.movieland.service.util.MovieToMovieDtoConverter;
import com.mirus.movieland.web.annotation.access.Secured;
import com.mirus.movieland.data.Role;
import com.mirus.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    List<MovieDto> getAll(HttpServletRequest request,
                          @RequestParam(value = "rating", required = false) String ratingSortOrder,
                          @RequestParam(value = "price", required = false) String priceSortOrder) {

        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            log.info("Logged user " + user.getEmail());
        }

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
    public Movie getMovieById(@PathVariable int id, @RequestParam(value = "currency", required = false) String currency) {
        if (currency != null) {
            return movieService.findById(id, Currency.fromValue(currency));
        }
        return movieService.findById(id);
    }

    @Secured(Role.ADMIN)
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void add(@RequestBody UpdateMovieDto movieDto) {
        Movie movie = MovieDtoConverter.convert(movieDto);
        movieService.save(movie, movieDto.getGenres(), movieDto.getCountries());
    }

    @Secured(Role.ADMIN)
    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void update(@PathVariable("id") int id, @RequestBody UpdateMovieDto movieDto) {
        Movie movie = MovieDtoConverter.convert(movieDto);
        movie.setId(id);
        movieService.update(movie, movieDto.getGenres(), movieDto.getCountries());
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
