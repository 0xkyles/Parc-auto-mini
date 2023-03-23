package com.example.parautomini.Mappers;

import com.example.parautomini.DTOs.Response.DriverDTO;
import com.example.parautomini.DTOs.Response.UserDTO;
import com.example.parautomini.Entites.Driver;
import com.example.parautomini.Entites.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DriverMapper implements IMapper<Driver, DriverDTO>{
    private final ModelMapper modelMapper;

    @Override
    public Driver toEntity(DriverDTO d) {
        return modelMapper.map(d, Driver.class);
    }

    @Override
    public DriverDTO toDTO(Driver e) {
        return modelMapper.map(e, DriverDTO.class);
    }

    @Override
    public DriverDTO objToDTO(Object o) {
        return modelMapper.map(o, DriverDTO.class);
    }

    @Override
    public Driver objToEntity(Object o) {
        return modelMapper.map(o, Driver.class);
    }
}
