package com.mirus.movieland.service.impl;

import com.mirus.movieland.entity.User;
import com.mirus.movieland.data.Session;
import com.mirus.movieland.service.SecurityService;
import com.mirus.movieland.service.UserService;
import com.mirus.movieland.service.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    private Map<String, Session> sessions = new ConcurrentHashMap<>();

    @Override
    public Session login(String email, String password) {
        Session session = userService.findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .map(Session::new)
                .orElseThrow(() -> new UnauthorizedException("User with email " + email + " not authorized"));

        sessions.put(session.getUuid(), session);
        return session;
    }

    @Override
    public void logout(String uuid) {
        sessions.remove(uuid);
    }

    @Override
    @Scheduled(cron = "${security.session.clean.cron.expression}")
    public void cleanExpiredSessions() {
        sessions.entrySet().removeIf(e -> e.getValue().isExpired());
    }

    @Override
    public Optional<User> getLoggedUserByUuid(String uuid) {
        Session session = sessions.get(uuid);
        return (session == null) ? Optional.empty() : Optional.of(session.getUser());
    }
}
