package com.mirus.movieland.controller;

import com.mirus.movieland.entity.Country;
import com.mirus.movieland.entity.Genre;
import com.mirus.movieland.service.CountryService;
import com.mirus.movieland.service.GenreService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:testContext.xml", "file:src/main/webapp/WEB-INF/movieland-servlet.xml", "classpath:spring/root-context.xml"})

public class CountryControllerTest {

    private MockMvc mockMvc;
    private List<Country> countries;

    @Autowired
    private CountryService countryService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        Mockito.reset(countryService);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Country country1 = new Country(1, "USA");
        Country country2 = new Country(2, "USSR");

        countries = Arrays.asList(country1, country2);
    }

    @Test
    public void testGetAllGenres() throws Exception {
        when(countryService.findAll()).thenReturn(countries);

        mockMvc.perform(get("/country"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("USA")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("USSR")));
    }
}