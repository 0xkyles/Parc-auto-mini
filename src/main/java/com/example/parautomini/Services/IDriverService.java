package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Requests.DriverReq;
import com.example.parautomini.DTOs.Requests.UserUpdateReq;
import com.example.parautomini.DTOs.Response.DriverDTO;

import java.util.List;

public interface IDriverService {
    DriverDTO add(DriverReq user);

    DriverDTO get(int driverId);

    List<DriverDTO> getAll();

    DriverDTO update(int userId, DriverReq user);

    void delete(int driverId);
}
