package com.mirus.movieland.config;

import com.mirus.movieland.security.SecurityService;
import com.mirus.movieland.service.CountryService;
import com.mirus.movieland.service.GenreService;
import com.mirus.movieland.service.MovieService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class TestContext {
    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setName("testDB;MODE=MYSQL")
                .setType(EmbeddedDatabaseType.H2)
                .addScript("db/createDatabase.sql")
                .build();

        return db;
    }

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






