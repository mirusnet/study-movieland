package com.mirus.movieland.security.data;

import com.mirus.movieland.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Session {
    private static final int SESSION_DURATION_HOURS = 2;

    private final User user;
    private final String uuid;
    private final LocalDateTime expiryDateTime;

    public Session(User user) {
        this.user = user;
        this.uuid = UUID.randomUUID().toString();
        this.expiryDateTime = LocalDateTime.now().plusHours(SESSION_DURATION_HOURS);
    }

    public boolean isExpired() {
        return expiryDateTime.isBefore(LocalDateTime.now());
    }
}
