package com.mirus.movieland.security.interceptor;

import com.mirus.movieland.entity.User;
import com.mirus.movieland.security.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthInterceptor extends HandlerInterceptorAdapter {
    private static final User ANONYMOUS_USER = new User(-1, "Anonymous", "an@an", "pass");

    private final SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uuid = request.getHeader("uuid");
        HttpSession session = request.getSession();
        User user = ANONYMOUS_USER;

        if (uuid != null) {
            user = securityService.getLoggedUserByUuid(uuid).orElse(ANONYMOUS_USER);
        }

        session.setAttribute("user", user);
        return true;
    }
}
