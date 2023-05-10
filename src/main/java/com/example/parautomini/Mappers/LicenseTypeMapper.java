
package com.example.parautomini.Mappers;

import com.example.parautomini.DTOs.Response.LicenseTypeDTO;
import com.example.parautomini.Entites.LicenseType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LicenseTypeMapper implements IMapper<LicenseType, LicenseTypeDTO>{
    private final ModelMapper modelMapper;

    @Override
    public LicenseType toEntity(LicenseTypeDTO d) {
        return modelMapper.map(d, LicenseType.class);
    }

    @Override
    public LicenseTypeDTO toDTO(LicenseType e) {
        return modelMapper.map(e, LicenseTypeDTO.class);
    }

    @Override
    public LicenseTypeDTO objToDTO(Object o) {
        return modelMapper.map(o, LicenseTypeDTO.class);
    }

    @Override
    public LicenseType objToEntity(Object o) {
        return modelMapper.map(o, LicenseType.class);
    }
}