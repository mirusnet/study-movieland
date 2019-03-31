package com.mirus.movieland.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Genre {
    private final int id;
    private final String name;
}
