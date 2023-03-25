package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Response.VehicleDTO;
import com.example.parautomini.DTOs.Response.VehicleTypeDTO;

import java.util.List;

public interface IVehicleTypeService {
    List<VehicleTypeDTO> getAll();
    VehicleTypeDTO get(String vehicleTypeId);
    List<VehicleTypeDTO> getByCategory(String vehicleCategoryId);
}
