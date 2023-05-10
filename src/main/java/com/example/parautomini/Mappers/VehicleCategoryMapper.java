package com.example.parautomini.Mappers;

import com.example.parautomini.DTOs.Response.VehicleCategoryDTO;
import com.example.parautomini.DTOs.Response.VehicleTypeDTO;
import com.example.parautomini.Entites.VehicleCategory;
import com.example.parautomini.Entites.VehicleType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleCategoryMapper implements IMapper<VehicleCategory, VehicleCategoryDTO>{
    private final ModelMapper modelMapper;

    @Override
    public VehicleCategory toEntity(VehicleCategoryDTO d) {
        return modelMapper.map(d, VehicleCategory.class);
    }

    @Override
    public VehicleCategoryDTO toDTO(VehicleCategory e) {
        return modelMapper.map(e, VehicleCategoryDTO.class);
    }

    @Override
    public VehicleCategoryDTO objToDTO(Object o) {
        return modelMapper.map(o, VehicleCategoryDTO.class);
    }

    @Override
    public VehicleCategory objToEntity(Object o) {
        return modelMapper.map(o, VehicleCategory.class);
    }
}
