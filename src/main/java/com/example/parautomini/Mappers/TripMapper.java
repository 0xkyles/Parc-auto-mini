package com.example.parautomini.Mappers;

import com.example.parautomini.DTOs.Response.TripDTO;
import com.example.parautomini.Entites.Trip;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TripMapper implements IMapper<Trip, TripDTO>{
    private final ModelMapper modelMapper;

    @Override
    public Trip toEntity(TripDTO d) {
        return modelMapper.map(d, Trip.class);
    }

    @Override
    public TripDTO toDTO(Trip e) {
        var trip = modelMapper.map(e, TripDTO.class);
        trip.setVehicleType(e.getVehicleType().getLabel());

        return trip;
    }

    @Override
    public TripDTO objToDTO(Object o) {
        return modelMapper.map(o, TripDTO.class);
    }

    @Override
    public Trip objToEntity(Object o) {
        return modelMapper.map(o, Trip.class);
    }
}
