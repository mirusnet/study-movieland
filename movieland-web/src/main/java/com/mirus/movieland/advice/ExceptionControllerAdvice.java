package com.mirus.movieland.advice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionResponseEntity sendBadRequestResponse(Exception exception) {
        ExceptionResponseEntity exceptionResponseEntity = new ExceptionResponseEntity();
        exceptionResponseEntity.setMessage(exception.getMessage());
        exceptionResponseEntity.setHttpStatus(HttpStatus.BAD_REQUEST);
        return exceptionResponseEntity;
    }

    @Getter
    @Setter
    private static class ExceptionResponseEntity {
        private String Message;
        private HttpStatus httpStatus;
    }
}
