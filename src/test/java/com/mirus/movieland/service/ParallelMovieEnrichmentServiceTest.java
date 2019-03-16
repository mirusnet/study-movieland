package com.mirus.movieland.service;

import com.mirus.movieland.entity.Country;
import com.mirus.movieland.entity.Genre;
import com.mirus.movieland.entity.Movie;
import com.mirus.movieland.entity.Review;
import com.mirus.movieland.entity.User;
import com.mirus.movieland.service.impl.ParallelMovieEnrichmentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/root-context.xml"})
public class ParallelMovieEnrichmentServiceTest {

    @Autowired
    private ExecutorService executorService;

    private GenreService genreService = Mockito.mock(GenreService.class);
    private CountryService countryService = Mockito.mock(CountryService.class);
    private ReviewService reviewService = Mockito.mock(ReviewService.class);


    @Test
    public void testFullEnrichment() {
        ParallelMovieEnrichmentServiceImpl parallelMovieEnrichmentService = new ParallelMovieEnrichmentServiceImpl(
                executorService,
                genreService,
                countryService,
                reviewService
        );
        parallelMovieEnrichmentService.setEnrichmentTimeoutSeconds(5);


        when(genreService.findByMovieId(0)).thenReturn(Arrays.asList(new Genre(0, "comedy")));
        when(countryService.findByMovieId(0)).thenReturn(Arrays.asList(new Country(0, "USA")));
        when(reviewService.findByMovieId(0)).thenReturn(Arrays.asList(new Review(0, "hlamAneKino", new User())));

        Movie movie = new Movie();
        movie.setId(0);

        parallelMovieEnrichmentService.enrich(movie);

        assertEquals("comedy", movie.getGenres().get(0).getName());
        assertEquals("USA", movie.getCountries().get(0).getName());
        assertEquals("hlamAneKino", movie.getReviews().get(0).getText());
    }

    @Test
    public void testPartialEnrichment() {
        ParallelMovieEnrichmentServiceImpl parallelMovieEnrichmentService = new ParallelMovieEnrichmentServiceImpl(
                executorService,
                genreService,
                countryService,
                reviewService
        );
        parallelMovieEnrichmentService.setEnrichmentTimeoutSeconds(2);



        when(genreService.findByMovieId(0)).thenAnswer((Answer<List<Genre>>) invocation -> {
            Thread.sleep(4000);
            return Arrays.asList(new Genre(0, "comedy"));
        });

        when(countryService.findByMovieId(0)).thenReturn(Arrays.asList(new Country(0, "USA")));
        when(reviewService.findByMovieId(0)).thenReturn(Arrays.asList(new Review(0, "hlamAneKino", new User())));

        Movie movie = new Movie();
        movie.setId(0);

        parallelMovieEnrichmentService.enrich(movie);

        assertNull(movie.getGenres());
        assertEquals("USA", movie.getCountries().get(0).getName());
        assertEquals("hlamAneKino", movie.getReviews().get(0).getText());
    }
}