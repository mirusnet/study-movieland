package com.mirus.movieland.service;

import com.mirus.movieland.entity.Country;

import java.util.List;

public interface CountryService {
    List<Country> findByMovieId(int id);
}
