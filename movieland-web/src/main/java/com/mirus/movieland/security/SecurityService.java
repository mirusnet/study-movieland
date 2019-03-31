package com.mirus.movieland.security;

import com.mirus.movieland.data.Session;
import com.mirus.movieland.entity.User;

import java.util.Optional;

public interface SecurityService {
    Session login(String email, String password);

    void logout(String uuid);

    void cleanExpiredSessions();

    Optional<User> getLoggedUserByUuid(String uuid);
}
