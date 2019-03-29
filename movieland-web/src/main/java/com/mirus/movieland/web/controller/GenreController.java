package com.mirus.movieland.web.controller;

import com.mirus.movieland.entity.Genre;
import com.mirus.movieland.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/genre")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<Genre> getAll() {
        return genreService.findAll();
    }
}
