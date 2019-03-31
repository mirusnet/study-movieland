package com.mirus.movieland.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mirus.movieland.config.RootConfig;
import com.mirus.movieland.config.TestContext;
import com.mirus.movieland.config.WebConfig;
import com.mirus.movieland.data.Role;
import com.mirus.movieland.data.Session;
import com.mirus.movieland.dto.LoginRequestDto;
import com.mirus.movieland.entity.User;
import com.mirus.movieland.security.SecurityService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RootConfig.class, WebConfig.class, TestContext.class})
public class LoginControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    SecurityService securityService;

    @Before
    public void setUp() {
       Mockito.reset(securityService);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testOkStatusForAllGenres() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        String userName = "name";
        String userEmail = "test@test.com";
        String password = "Alexander do not steal my code!!!";

        User user = new User(1, userName, userEmail, Role.ANONYMOUS, password);

        String request = objectMapper.writeValueAsString(new LoginRequestDto(userEmail, password));
        when(securityService.login(userEmail, password)).thenReturn(new Session(user));
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
                .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.nickname", is(userName)));
    }
}