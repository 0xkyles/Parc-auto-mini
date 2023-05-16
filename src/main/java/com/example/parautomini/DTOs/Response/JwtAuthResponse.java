package com.example.parautomini.DTOs.Response;

import com.example.parautomini.token.TokenType;

public record JwtAuthResponse (String token, TokenType type){
}
