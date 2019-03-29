package com.mirus.movieland.web.controller;


import com.mirus.movieland.data.Session;
import com.mirus.movieland.dto.LoginRequestDto;
import com.mirus.movieland.dto.LoginResponceDto;
import com.mirus.movieland.service.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final SecurityService securityService;

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    LoginResponceDto login(@RequestBody LoginRequestDto loginRequestDto) {
        Session session = securityService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        return new LoginResponceDto(session.getUuid(), session.getUser().getName());
    }

    @DeleteMapping(path = "/logout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void logout(@RequestHeader(value = "uuid") String uuid) {
        securityService.logout(uuid);
    }
}
