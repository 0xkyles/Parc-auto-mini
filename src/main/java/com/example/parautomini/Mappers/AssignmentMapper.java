package com.example.parautomini.Mappers;

import com.example.parautomini.DTOs.Response.AssignmentDTO;
import com.example.parautomini.DTOs.Response.HolidayDTO;
import com.example.parautomini.Entites.Assignment;
import com.example.parautomini.Entites.Holiday;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssignmentMapper implements IMapper<Assignment, AssignmentDTO>{
    private final ModelMapper modelMapper;

    @Override
    public Assignment toEntity(AssignmentDTO d) {
        return modelMapper.map(d, Assignment.class);
    }

    @Override
    public AssignmentDTO toDTO(Assignment e) {
        var res = modelMapper.map(e, AssignmentDTO.class);
        res.setDriver(e.getDriver().getFirstName() + " " + e.getDriver().getLastName());
        res.setStartDate(e.getTrip().getStartDate());
        res.setEndDate(e.getTrip().getEndDate());
        res.setV_registrationNumber(e.getVehicle().getV_registrationNumber());
        res.setVehicleType(e.getVehicle().getVehicleType().getLabel());

        return res;
    }

    @Override
    public AssignmentDTO objToDTO(Object o) {
        return modelMapper.map(o, AssignmentDTO.class);
    }

    @Override
    public Assignment objToEntity(Object o) {
        return modelMapper.map(o, Assignment.class);
    }
}
