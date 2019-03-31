package com.mirus.movieland.controller;

import com.mirus.movieland.config.RootConfig;
import com.mirus.movieland.config.TestContext;
import com.mirus.movieland.config.WebConfig;
import com.mirus.movieland.data.Role;
import com.mirus.movieland.entity.Country;
import com.mirus.movieland.entity.Currency;
import com.mirus.movieland.entity.Genre;
import com.mirus.movieland.entity.Movie;
import com.mirus.movieland.entity.Review;
import com.mirus.movieland.entity.User;
import com.mirus.movieland.repository.data.SortParameters;
import com.mirus.movieland.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RootConfig.class, WebConfig.class, TestContext.class})
public class MovieControllerTest {

    private MockMvc mockMvc;

    private List<Movie> movies;

    private Movie movieWithDetails = new Movie();

    @Autowired
    @Qualifier("movieServiceMock")
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
        movie.setPrice(48.48);
        movie.setYearOfRelease("1986");

        Movie movie1 = new Movie();
        movie1.setId(49);
        movie1.setNameNative("native_two");
        movie1.setNameRussian("russian_two");
        movie1.setDescription("description_two");
        movie1.setRating(49.49);
        movie1.setPrice(49.49);
        movie1.setYearOfRelease("1986");

        movies = Arrays.asList(movie, movie1);

        movieWithDetails.setId(48);
        movieWithDetails.setNameNative("native");
        movieWithDetails.setNameRussian("russian");
        movieWithDetails.setDescription("description");
        movieWithDetails.setRating(48.48);
        movieWithDetails.setPrice(48.48);
        movieWithDetails.setYearOfRelease("1986");

        Country country0 = new Country(0, "USA");
        Country country1 = new Country(1, "Canada");

        Genre genre0 = new Genre(0, "Comedy");
        Genre genre1 = new Genre(1, "Detective");

        User user = new User(0, "Anonimous", "an@db.com", Role.ANONYMOUS, "password");
        Review review = new Review(0, "SomeText", user, new Movie());

        movieWithDetails.setCountries(Arrays.asList(country0, country1));
        movieWithDetails.setGenres(Arrays.asList(genre0, genre1));
        movieWithDetails.setReviews(Arrays.asList(review));
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
    public void testOkStatusForGetMoviesByGenreId() throws Exception {
        mockMvc.perform(get("/movie/genre/1"))
                .andExpect(status().isOk());
    }


    @Test
    public void testGetAllMovies() throws Exception {
        when(movieService.findAll()).thenReturn(movies);

        mockMvc.perform(get("/movie"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].id", is(48)))
                .andExpect(jsonPath("$[0].nameNative", is("native")))
                .andExpect(jsonPath("$[0].nameRussian", is("russian")))
                .andExpect(jsonPath("$[0].price", is(48.48)))
                .andExpect(jsonPath("$[0].rating", is(48.48)))
                .andExpect(jsonPath("$[0].yearOfRelease", is("1986")))

                .andExpect(jsonPath("$[1].id", is(49)))
                .andExpect(jsonPath("$[1].nameNative", is("native_two")))
                .andExpect(jsonPath("$[1].nameRussian", is("russian_two")))
                .andExpect(jsonPath("$[1].price", is(49.49)))
                .andExpect(jsonPath("$[1].rating", is(49.49)))
                .andExpect(jsonPath("$[1].yearOfRelease", is("1986")));
    }


    @Test
    public void testGetRandomMovies() throws Exception {
        when(movieService.findRandom()).thenReturn(movies);

        mockMvc.perform(get("/movie/random"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].id", is(48)))
                .andExpect(jsonPath("$[0].nameNative", is("native")))
                .andExpect(jsonPath("$[0].nameRussian", is("russian")))
                .andExpect(jsonPath("$[0].price", is(48.48)))
                .andExpect(jsonPath("$[0].rating", is(48.48)))
                .andExpect(jsonPath("$[0].yearOfRelease", is("1986")))

                .andExpect(jsonPath("$[1].id", is(49)))
                .andExpect(jsonPath("$[1].nameNative", is("native_two")))
                .andExpect(jsonPath("$[1].nameRussian", is("russian_two")))
                .andExpect(jsonPath("$[1].price", is(49.49)))
                .andExpect(jsonPath("$[1].rating", is(49.49)))
                .andExpect(jsonPath("$[1].yearOfRelease", is("1986")));
    }

    @Test
    public void testGetMoviesByGenreId() throws Exception {
        when(movieService.findByGenreId(1)).thenReturn(movies);

        mockMvc.perform(get("/movie/genre/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].id", is(48)))
                .andExpect(jsonPath("$[0].nameNative", is("native")))
                .andExpect(jsonPath("$[0].nameRussian", is("russian")))
                .andExpect(jsonPath("$[0].price", is(48.48)))
                .andExpect(jsonPath("$[0].rating", is(48.48)))
                .andExpect(jsonPath("$[0].yearOfRelease", is("1986")))

                .andExpect(jsonPath("$[1].id", is(49)))
                .andExpect(jsonPath("$[1].nameNative", is("native_two")))
                .andExpect(jsonPath("$[1].nameRussian", is("russian_two")))
                .andExpect(jsonPath("$[1].price", is(49.49)))
                .andExpect(jsonPath("$[1].rating", is(49.49)))
                .andExpect(jsonPath("$[1].yearOfRelease", is("1986")));
    }

    @Test
    public void testGetAllMoviesWithRatingSorting() throws Exception {
        SortParameters sortParameters = new SortParameters("rating", SortParameters.SortDirection.fromValue("desc"));
        when(movieService.findAll(sortParameters)).thenReturn(movies);

        mockMvc.perform(get("/movie?rating=desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].id", is(48)))
                .andExpect(jsonPath("$[0].nameNative", is("native")))
                .andExpect(jsonPath("$[0].nameRussian", is("russian")))
                .andExpect(jsonPath("$[0].price", is(48.48)))
                .andExpect(jsonPath("$[0].rating", is(48.48)))
                .andExpect(jsonPath("$[0].yearOfRelease", is("1986")))

                .andExpect(jsonPath("$[1].id", is(49)))
                .andExpect(jsonPath("$[1].nameNative", is("native_two")))
                .andExpect(jsonPath("$[1].nameRussian", is("russian_two")))
                .andExpect(jsonPath("$[1].price", is(49.49)))
                .andExpect(jsonPath("$[1].rating", is(49.49)))
                .andExpect(jsonPath("$[1].yearOfRelease", is("1986")));
    }

    @Test
    public void testGetAllMoviesWithPriceSorting() throws Exception {
        SortParameters sortParameters = new SortParameters("price", SortParameters.SortDirection.fromValue("asc"));
        when(movieService.findAll(sortParameters)).thenReturn(movies);

        mockMvc.perform(get("/movie?price=asc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].id", is(48)))
                .andExpect(jsonPath("$[0].nameNative", is("native")))
                .andExpect(jsonPath("$[0].nameRussian", is("russian")))
                .andExpect(jsonPath("$[0].price", is(48.48)))
                .andExpect(jsonPath("$[0].rating", is(48.48)))
                .andExpect(jsonPath("$[0].yearOfRelease", is("1986")))

                .andExpect(jsonPath("$[1].id", is(49)))
                .andExpect(jsonPath("$[1].nameNative", is("native_two")))
                .andExpect(jsonPath("$[1].nameRussian", is("russian_two")))
                .andExpect(jsonPath("$[1].price", is(49.49)))
                .andExpect(jsonPath("$[1].rating", is(49.49)))
                .andExpect(jsonPath("$[1].yearOfRelease", is("1986")));
    }

    @Test
    public void testMovieById() throws Exception {
        when(movieService.findById(48)).thenReturn(movieWithDetails);

        mockMvc.perform(get("/movie/48"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(jsonPath("$.id", is(48)))
                .andExpect(jsonPath("$.nameNative", is("native")))
                .andExpect(jsonPath("$.nameRussian", is("russian")))
                .andExpect(jsonPath("$.price", is(48.48)))
                .andExpect(jsonPath("$.rating", is(48.48)))
                .andExpect(jsonPath("$.yearOfRelease", is("1986")))

                .andExpect(jsonPath("$.genres", hasSize(2)))
                .andExpect(jsonPath("$.countries", hasSize(2)))
                .andExpect(jsonPath("$.reviews", hasSize(1)))

                .andExpect(jsonPath("$.genres[0].id", is(0)))
                .andExpect(jsonPath("$.genres[0].name", is("Comedy")))
                .andExpect(jsonPath("$.genres[1].id", is(1)))
                .andExpect(jsonPath("$.genres[1].name", is("Detective")))

                .andExpect(jsonPath("$.reviews[0].id", is(0)))
                .andExpect(jsonPath("$.reviews[0].text", is("SomeText")))

                .andExpect(jsonPath("$.reviews[0].user.id", is(0)))
                .andExpect(jsonPath("$.reviews[0].user.name", is("Anonimous")))
                .andExpect(jsonPath("$.reviews[0].user.email", is("an@db.com")))

                .andExpect(jsonPath("$.reviews[0].user.password").doesNotExist());
    }

    @Test
    public void testMovieByIdWithCurrency() throws Exception {
        when(movieService.findById(48, Currency.USD)).thenReturn(movieWithDetails);

        mockMvc.perform(get("/movie/48?currency=USD"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(jsonPath("$.id", is(48)))
                .andExpect(jsonPath("$.nameNative", is("native")))
                .andExpect(jsonPath("$.nameRussian", is("russian")))
                .andExpect(jsonPath("$.price", is(48.48)))
                .andExpect(jsonPath("$.rating", is(48.48)))
                .andExpect(jsonPath("$.yearOfRelease", is("1986")))

                .andExpect(jsonPath("$.genres", hasSize(2)))
                .andExpect(jsonPath("$.countries", hasSize(2)))
                .andExpect(jsonPath("$.reviews", hasSize(1)))

                .andExpect(jsonPath("$.genres[0].id", is(0)))
                .andExpect(jsonPath("$.genres[0].name", is("Comedy")))
                .andExpect(jsonPath("$.genres[1].id", is(1)))
                .andExpect(jsonPath("$.genres[1].name", is("Detective")))

                .andExpect(jsonPath("$.reviews[0].id", is(0)))
                .andExpect(jsonPath("$.reviews[0].text", is("SomeText")))

                .andExpect(jsonPath("$.reviews[0].user.id", is(0)))
                .andExpect(jsonPath("$.reviews[0].user.name", is("Anonimous")))
                .andExpect(jsonPath("$.reviews[0].user.email", is("an@db.com")))

                .andExpect(jsonPath("$.reviews[0].user.password").doesNotExist());
    }

    @Test
    public void testAddMovieRequestSecurity() throws Exception {
        mockMvc.perform(post("/movie/"))
                .andExpect(status().is(401));
    }

    @Test
    public void testUpdateMovieRequestSecurity() throws Exception {
        mockMvc.perform(put("/movie/48"))
                .andExpect(status().is(401));
    }
}