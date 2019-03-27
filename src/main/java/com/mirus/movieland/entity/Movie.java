package com.mirus.movieland.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class Movie {
    private int id;
    private String nameRussian;
    private String nameNative;
    private String yearOfRelease;
    private String description;
    private double rating;
    private double price;
    private String picturePath;

    private List<Country> countries;
    private List<Genre> genres;
    private List<Review> reviews;

    public Movie() {
    }

    public Movie(Movie movie) {
        id = movie.getId();
        nameRussian = movie.getNameRussian();
        nameNative = movie.getNameNative();
        yearOfRelease = movie.getYearOfRelease();
        description = movie.getDescription();
        rating = movie.getRating();
        price = movie.getPrice();
        picturePath = movie.getPicturePath();

        countries = (movie.getCountries() == null) ? Collections.emptyList() : Collections.unmodifiableList(movie.getCountries());
        genres = (movie.getGenres() == null) ? Collections.emptyList() : Collections.unmodifiableList(movie.getGenres());
        reviews = (movie.getReviews() == null) ? Collections.emptyList() : Collections.unmodifiableList(movie.getReviews());
    }
}
