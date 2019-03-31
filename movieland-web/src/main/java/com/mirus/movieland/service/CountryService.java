package com.mirus.movieland.service;

import com.mirus.movieland.entity.Country;

import java.util.List;

public interface CountryService extends MovieEnrichable{
    List<Country> findByMovieId(int id);

    List<Country> findAll();

    void deleteMappingByMovieId(int movieId);

    void insertMappingByMovieId(int movieId, int[] countryIds);
}
