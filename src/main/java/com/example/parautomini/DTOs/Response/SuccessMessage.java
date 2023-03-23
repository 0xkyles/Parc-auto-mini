package com.example.parautomini.DTOs.Response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Data
public class SuccessMessage {
    private final String message;
    private final HttpStatus status;
}
