package com.mirus.movieland.service.impl;

import com.mirus.movieland.entity.Movie;
import com.mirus.movieland.repository.MovieRepository;
import com.mirus.movieland.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
}
