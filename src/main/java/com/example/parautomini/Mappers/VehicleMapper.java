package com.example.parautomini.Mappers;

import com.example.parautomini.DTOs.Response.VehicleDTO;
import com.example.parautomini.Entites.Vehicle;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleMapper implements IMapper<Vehicle, VehicleDTO>{
    private final ModelMapper modelMapper;

    @Override
    public Vehicle toEntity(VehicleDTO d) {
        return modelMapper.map(d, Vehicle.class);
    }

    @Override
    public VehicleDTO toDTO(Vehicle e) {
        var vehicleDTO = modelMapper.map(e, VehicleDTO.class);
        vehicleDTO.setVehicleType(e.getVehicleType().getLabel());
        vehicleDTO.setVehicleCategory(e.getVehicleType().getVehicleCategory().getLabel());

        return vehicleDTO;
    }

    @Override
    public VehicleDTO objToDTO(Object o) {
        return modelMapper.map(o, VehicleDTO.class);
    }

    @Override
    public Vehicle objToEntity(Object o) {
        return modelMapper.map(o, Vehicle.class);
    }
}
