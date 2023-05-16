package com.example.parautomini.auth;


import com.example.parautomini.DTOs.Requests.LoginRequestDto;

public interface IAuthService {
    String login(LoginRequestDto loginDto);
}
