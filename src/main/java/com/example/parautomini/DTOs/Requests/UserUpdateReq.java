package com.example.parautomini.DTOs.Requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateReq {
    @Email(message = "Email must be valid")
    @NotEmpty(message = "Email must not be blank")
    private String email;
    @NotEmpty(message = "Password must not be blank")
    private String password;
}