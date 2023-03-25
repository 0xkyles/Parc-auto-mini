
package com.example.parautomini.Mappers;

import com.example.parautomini.DTOs.Response.PermitTypeDTO;
import com.example.parautomini.Entites.PermitType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PermitTypeMapper implements IMapper<PermitType, PermitTypeDTO>{
    private final ModelMapper modelMapper;

    @Override
    public PermitType toEntity(PermitTypeDTO d) {
        return modelMapper.map(d, PermitType.class);
    }

    @Override
    public PermitTypeDTO toDTO(PermitType e) {
        return modelMapper.map(e, PermitTypeDTO.class);
    }

    @Override
    public PermitTypeDTO objToDTO(Object o) {
        return modelMapper.map(o, PermitTypeDTO.class);
    }

    @Override
    public PermitType objToEntity(Object o) {
        return modelMapper.map(o, PermitType.class);
    }
}