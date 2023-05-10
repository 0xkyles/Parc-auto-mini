package com.example.parautomini.Mappers;

import com.example.parautomini.DTOs.Response.HolidayDTO;
import com.example.parautomini.DTOs.Response.VehicleDTO;
import com.example.parautomini.Entites.Holiday;
import com.example.parautomini.Entites.Vehicle;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HolidayMapper implements IMapper<Holiday, HolidayDTO>{
    private final ModelMapper modelMapper;

    @Override
    public Holiday toEntity(HolidayDTO d) {
        return modelMapper.map(d, Holiday.class);
    }

    @Override
    public HolidayDTO toDTO(Holiday e) {
        var holidayDTO = modelMapper.map(e, HolidayDTO.class);
        holidayDTO.setDriverId(e.getDriver().getDriverId());

        return holidayDTO;
    }

    @Override
    public HolidayDTO objToDTO(Object o) {
        return modelMapper.map(o, HolidayDTO.class);
    }

    @Override
    public Holiday objToEntity(Object o) {
        return modelMapper.map(o, Holiday.class);
    }
}
