package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Response.VehicleCategoryDTO;
import com.example.parautomini.DTOs.Response.VehicleTypeDTO;
import com.example.parautomini.Entites.VehicleCategory;
import com.example.parautomini.Entites.VehicleType;
import com.example.parautomini.Exceptions.ResourceNotFoundException;
import com.example.parautomini.Mappers.VehicleCategoryMapper;
import com.example.parautomini.Mappers.VehicleTypeMapper;
import com.example.parautomini.Repositories.VehicleCategoryRepository;
import com.example.parautomini.Repositories.VehicleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleCategoryService implements IVehicleCategoryService {

    private final VehicleCategoryRepository vehicleCategoryRepository;
    private final VehicleCategoryMapper mapper;

    @Override
    public List<VehicleCategoryDTO> getAll() {
        return vehicleCategoryRepository.findAll().stream().map(mapper::toDTO).toList();
    }

    @Override
    public VehicleCategoryDTO get(String vehicleCategoryId) {
        var category = vehicleCategoryRepository.findByLabel(vehicleCategoryId).orElseThrow(
                () -> new ResourceNotFoundException("Vehicle category", "ID", vehicleCategoryId)
        );

        return mapper.toDTO(category);
    }
}
