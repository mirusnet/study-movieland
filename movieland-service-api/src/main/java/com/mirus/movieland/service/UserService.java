package com.mirus.movieland.service;

import com.mirus.movieland.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
}
