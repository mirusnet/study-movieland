package com.mirus.movieland.repository;

import com.mirus.movieland.entity.Country;

import java.util.List;

public interface CountryRepository {
    List<Country> findByMovieId(int id);
}
