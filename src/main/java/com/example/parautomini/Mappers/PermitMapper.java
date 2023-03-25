package com.example.parautomini.Mappers;

import com.example.parautomini.DTOs.Response.PermitDTO;
import com.example.parautomini.Entites.Permit;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PermitMapper implements IMapper<Permit, PermitDTO>{
    private final ModelMapper modelMapper;

    @Override
    public Permit toEntity(PermitDTO d) {
        return modelMapper.map(d, Permit.class);
    }

    @Override
    public PermitDTO toDTO(Permit e) {
        var permitDTO = modelMapper.map(e, PermitDTO.class);
        permitDTO.setDriverId(e.getDriver().getDriverId());
        permitDTO.setPermitType(e.getPermitType().getCategory().name());

        return permitDTO;
    }

    @Override
    public PermitDTO objToDTO(Object o) {
        return modelMapper.map(o, PermitDTO.class);
    }

    @Override
    public Permit objToEntity(Object o) {
        return modelMapper.map(o, Permit.class);
    }
}
