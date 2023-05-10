package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Response.VehicleCategoryDTO;
import com.example.parautomini.DTOs.Response.VehicleTypeDTO;

import java.util.List;

public interface IVehicleCategoryService {
    List<VehicleCategoryDTO> getAll();
    VehicleCategoryDTO get(String vehicleCategoryId);
}
