package com.mirus.movieland.service.impl;

import com.mirus.movieland.entity.Country;
import com.mirus.movieland.entity.Movie;
import com.mirus.movieland.repository.CountryRepository;
import com.mirus.movieland.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    @Override
    public List<Country> findByMovieId(int id) {
        return countryRepository.findByMovieId(id);
    }

    @Override
    public void enrich(Movie movie) {
        movie.setCountries(findByMovieId(movie.getId()));
    }
}
