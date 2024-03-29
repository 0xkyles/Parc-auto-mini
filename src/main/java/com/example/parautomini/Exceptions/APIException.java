package com.example.parautomini.Exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class APIException extends RuntimeException {
    private String message;
    private HttpStatus status;

    public APIException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }
}
