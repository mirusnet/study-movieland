package com.mirus.movieland.entity;

import lombok.Getter;
import lombok.Setter;

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
}
