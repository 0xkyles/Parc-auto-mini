package com.example.parautomini.DTOs.Response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
public class ErrorMessage {
    private final String message;
    private final HttpStatus status;
}
