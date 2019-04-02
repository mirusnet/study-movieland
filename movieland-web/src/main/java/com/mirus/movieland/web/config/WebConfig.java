package com.mirus.movieland.web.config;

import com.mirus.movieland.service.SecurityService;
import com.mirus.movieland.web.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
@ComponentScan(basePackages = {"com.mirus.movieland.web.controller"})
public class WebConfig implements WebMvcConfigurer {

    private final SecurityService securityService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor(securityService));
    }
}
