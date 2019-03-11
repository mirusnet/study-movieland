
package com.mirus.movieland.controller;

import com.mirus.movieland.entity.Genre;
import com.mirus.movieland.entity.Movie;
import com.mirus.movieland.service.GenreService;
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
public class GenreControllerTest {

    private MockMvc mockMvc;
    private List<Genre> genres;

    @Autowired
    private GenreService genreService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        Mockito.reset(genreService);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Genre genre = new Genre();
        genre.setId(0);
        genre.setName("comedy");

        Genre genre1 = new Genre();
        genre1.setId(1);
        genre1.setName("detective");

        genres = Arrays.asList(genre, genre1);
    }

    @Test
    public void testOkStatusForAllGenres() throws Exception {
        mockMvc.perform(get("/genre"))
                .andExpect(status().isOk());
    }


    @Test
    public void testGetAllGenres() throws Exception {
        when(genreService.findAll()).thenReturn(genres);

        mockMvc.perform(get("/genre"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(0)))
                .andExpect(jsonPath("$[0].name", is("comedy")))
                .andExpect(jsonPath("$[1].id", is(1)))
                .andExpect(jsonPath("$[1].name", is("detective")));
    }
}