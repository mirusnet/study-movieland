package com.mirus.movieland.controller;

import com.mirus.movieland.entity.Movie;
import com.mirus.movieland.service.MovieService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:testContext.xml", "classpath:movieland-servlet.xml", "classpath:spring/root-context.xml"})
public class MovieControllerTest {

    private MockMvc mockMvc;

    private List<Movie> movies;

    @Autowired
    private MovieService movieService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        Mockito.reset(movieService);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Movie movie = new Movie();
        movie.setId(48);
        movie.setNameNative("native");
        movie.setNameRussian("russian");
        movie.setDescription("description");
        movie.setRating(48.48);
        movie.setPrice(49.49);
        movie.setYearOfRelease("1986");
        movies = Arrays.asList(movie);
    }

    @Test
    public void testOkStatusForAllMovies() throws Exception {
        mockMvc.perform(get("/movie"))
                .andExpect(status().isOk());
    }

    @Test
    public void testOkStatusForRandomMovies() throws Exception {
        mockMvc.perform(get("/movie/random"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllMovies() throws Exception {
        when(movieService.findAll()).thenReturn(movies);

        mockMvc.perform(get("/movie"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(48)))
                .andExpect(jsonPath("$[0].nameNative", is("native")))
                .andExpect(jsonPath("$[0].nameRussian", is("russian")))
                .andExpect(jsonPath("$[0].price", is(49.49)))
                .andExpect(jsonPath("$[0].rating", is(48.48)))
                .andExpect(jsonPath("$[0].yearOfRelease", is("1986")));
    }


    @Test
    public void testGetRandomMovies() throws Exception {
        when(movieService.findRandom()).thenReturn(movies);

        mockMvc.perform(get("/movie/random"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(48)))
                .andExpect(jsonPath("$[0].nameNative", is("native")))
                .andExpect(jsonPath("$[0].nameRussian", is("russian")))
                .andExpect(jsonPath("$[0].price", is(49.49)))
                .andExpect(jsonPath("$[0].rating", is(48.48)))
                .andExpect(jsonPath("$[0].yearOfRelease", is("1986")));
    }
}