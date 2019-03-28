package com.mirus.movieland.config;

import com.mirus.movieland.security.SecurityService;
import com.mirus.movieland.service.CountryService;
import com.mirus.movieland.service.GenreService;
import com.mirus.movieland.service.MovieService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestContext {

    @Bean
    @Qualifier("movieServiceMock")
    public MovieService movieService() {
        return Mockito.mock(MovieService.class);
    }

    @Bean
    public GenreService genreService() {
        return Mockito.mock(GenreService.class);
    }

    @Bean
    public SecurityService securityService() {
        return Mockito.mock(SecurityService.class);
    }

    @Bean
    public CountryService countryService() {
        return Mockito.mock(CountryService.class);
    }
}






