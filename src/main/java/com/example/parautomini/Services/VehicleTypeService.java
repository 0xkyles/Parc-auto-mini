package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Response.VehicleTypeDTO;
import com.example.parautomini.Entites.VehicleCategory;
import com.example.parautomini.Entites.VehicleType;
import com.example.parautomini.Exceptions.ResourceNotFoundException;
import com.example.parautomini.Mappers.VehicleTypeMapper;
import com.example.parautomini.Repositories.VehicleCategoryRepository;
import com.example.parautomini.Repositories.VehicleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleTypeService implements IVehicleTypeService {

    private final VehicleTypeRepository vehicleTypeRepository;
    private final VehicleTypeMapper vehicleTypeMapper;
    private final VehicleCategoryRepository vehicleCategoryRepository;

    @Override
    public List<VehicleTypeDTO> getAll() {
        return vehicleTypeRepository.findAll().stream()
                .map(vehicleTypeMapper::toDTO)
                .toList();
    }

    @Override
    public VehicleTypeDTO get(String vehicleTypeId) {
        return vehicleTypeMapper.toDTO(this.findVehicleTypeById(vehicleTypeId));
    }

    @Override
    public List<VehicleTypeDTO> getByCategory(String vehicleCategoryId) {
        var category = this.findVehicleCategoryById(vehicleCategoryId);

        return vehicleTypeRepository.findAllByVehicleCategory(category).stream().map(vehicleTypeMapper::toDTO).toList();
    }

    private VehicleType findVehicleTypeById(String vehicleTypeId) {
        return vehicleTypeRepository.findByLabel(vehicleTypeId).orElseThrow(
                () -> new ResourceNotFoundException("Vehicle Type", "id", vehicleTypeId)
        );
    }

    private VehicleCategory findVehicleCategoryById(String vehicleCategoryId) {
        return vehicleCategoryRepository.findByLabel(vehicleCategoryId).orElseThrow(
                () -> new ResourceNotFoundException("Vehicle category", "id", vehicleCategoryId)
        );
    }
}
