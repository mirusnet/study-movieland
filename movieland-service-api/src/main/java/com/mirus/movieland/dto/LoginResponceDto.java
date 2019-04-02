package com.mirus.movieland.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponceDto {
    private final String uuid;
    private final String nickname;
}
