package com.example.parautomini.DTOs.Response;

import com.example.parautomini.Entites.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private int userId;
    private String email;
    private User.RoleEnum role;
}
