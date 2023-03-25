package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Response.VehicleDTO;
import com.example.parautomini.DTOs.Requests.VehicleReq;

import java.util.List;

public interface IVehicleService {
    VehicleDTO add(VehicleReq req);

    void delete(String v_registrationNumber);
    List<VehicleDTO> getAll();
    VehicleDTO getById(String v_registrationNumber);
    List<VehicleDTO> getAllByVehicleType(String vehicleType);
    VehicleDTO update(String v_registrationNumber, VehicleReq req);
}
