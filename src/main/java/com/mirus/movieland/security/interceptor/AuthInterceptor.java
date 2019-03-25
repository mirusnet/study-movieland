package com.mirus.movieland.security.interceptor;

import com.mirus.movieland.entity.User;
import com.mirus.movieland.security.SecurityService;
import com.mirus.movieland.security.annotation.access.Secured;
import com.mirus.movieland.security.data.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthInterceptor extends HandlerInterceptorAdapter {
    private static final User ANONYMOUS_USER = new User(-1, "Anonymous", "anonymous@an.com", Role.ANONYMOUS, "pass");

    private final SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        MDC.put("uuid", UUID.randomUUID().toString());

        String uuid = request.getHeader("uuid");
        HttpSession session = request.getSession();
        User user = ANONYMOUS_USER;

        if (uuid != null) {
            user = securityService.getLoggedUserByUuid(uuid).orElse(ANONYMOUS_USER);
        }
        MDC.put("user", user.getEmail());
        session.setAttribute("user", user);

        if (handler instanceof HandlerMethod) {
            Method method = ((HandlerMethod) handler).getMethod();
            if (method.isAnnotationPresent(Secured.class)) {
                return Arrays.asList(method.getAnnotation(Secured.class).value()).contains(user.getRole());
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        MDC.clear();
    }
}
