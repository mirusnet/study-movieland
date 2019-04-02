package com.mirus.movieland.web.controller;

import com.mirus.movieland.entity.Country;
import com.mirus.movieland.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/country")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<Country> getAll() {
        return countryService.findAll();
    }
}
