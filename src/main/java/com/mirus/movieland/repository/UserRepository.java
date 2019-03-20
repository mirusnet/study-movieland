package com.mirus.movieland.repository;

import com.mirus.movieland.entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
}
