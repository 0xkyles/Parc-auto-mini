package com.example.parautomini.Mappers;

import com.example.parautomini.DTOs.Response.VehicleDTO;
import com.example.parautomini.DTOs.Response.VehicleTypeDTO;
import com.example.parautomini.Entites.Vehicle;
import com.example.parautomini.Entites.VehicleType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleTypeMapper implements IMapper<VehicleType, VehicleTypeDTO>{
    private final ModelMapper modelMapper;

    @Override
    public VehicleType toEntity(VehicleTypeDTO d) {
        return modelMapper.map(d, VehicleType.class);
    }

    @Override
    public VehicleTypeDTO toDTO(VehicleType e) {
        var vehicleTypeDTO = modelMapper.map(e, VehicleTypeDTO.class);
        vehicleTypeDTO.setVehicleCategory(e.getVehicleCategory().getLabel());

        return vehicleTypeDTO;
    }

    @Override
    public VehicleTypeDTO objToDTO(Object o) {
        return modelMapper.map(o, VehicleTypeDTO.class);
    }

    @Override
    public VehicleType objToEntity(Object o) {
        return modelMapper.map(o, VehicleType.class);
    }
}
