package com.mirus.movieland.web.annotation.access;

import com.mirus.movieland.data.Role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Secured {
    Role[] value() default Role.ADMIN;
}

