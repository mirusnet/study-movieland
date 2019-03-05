package com.mirus.movieland.data.dto;

import com.mirus.movieland.entity.Movie;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDto {
    private int id;
    private String nameRussian;
    private String nameNative;
    private String yearOfRelease;
    private double rating;
    private double price;
    private String picturePath;

    public MovieDto(Movie movie) {
        this.id = movie.getId();
        this.nameRussian = movie.getNameRussian();
        this.nameNative = movie.getNameNative();
        this.yearOfRelease = movie.getYearOfRelease();
        this.rating = movie.getRating();
        this.price = movie.getPrice();
        this.picturePath = movie.getPicturePath();
    }
}
