package com.example.parautomini.auth;

import com.example.parautomini.DTOs.Requests.LoginRequestDto;
import com.example.parautomini.DTOs.Response.JwtAuthResponse;
import com.example.parautomini.Services.UserService;
import com.example.parautomini.token.TokenType;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final IAuthService IAuthService;
    private final UserService userService;

    public AuthController(IAuthService IAuthService, UserService userService) {
        this.IAuthService = IAuthService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> loginHandler(
            @Valid @RequestBody LoginRequestDto loginDto) {
        String token = IAuthService.login(loginDto);

        JwtAuthResponse response = new JwtAuthResponse(token, TokenType.BEARER);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
