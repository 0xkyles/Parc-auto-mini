package com.example.parautomini.DTOs.Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto{
        @NotNull(message = "Cin is required")
        @NotBlank(message = "Cin should not be blank")
        private String email;
        @NotNull(message = "Password is required")
        @NotBlank(message = "Password should not be blank")
        private String password;
}
