package com.mirus.movieland.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateMovieDto {
    private String nameRussian;
    private String nameNative;
    private String yearOfRelease;
    private String description;
    private double price;
    private double rating;
    private String picturePath;
    int[] countries;
    int[] genres;
}
