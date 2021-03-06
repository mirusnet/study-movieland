package com.mirus.movieland.service;

import com.mirus.movieland.entity.User;
import com.mirus.movieland.data.Session;

import java.util.Optional;

public interface SecurityService {
    Session login(String email, String password);

    void logout(String uuid);

    void cleanExpiredSessions();

    Optional<User> getLoggedUserByUuid(String uuid);
}
