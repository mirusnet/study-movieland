package com.mirus.movieland.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mirus.movieland.security.data.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private String email;

    private Role role;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
