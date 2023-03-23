package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Requests.UserCreateReq;
import com.example.parautomini.DTOs.Requests.UserUpdateReq;
import com.example.parautomini.DTOs.Response.UserDTO;

import java.util.List;

public interface IUserService {
    UserDTO register(UserCreateReq user);

    UserDTO get(int userId);

    List<UserDTO> getAll();

    UserDTO update(int userId, UserUpdateReq user);

    void delete(int userId);
}
