package com.example.parautomini.Mappers;

import com.example.parautomini.DTOs.Response.LicenseDTO;
import com.example.parautomini.Entites.License;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LicenseMapper implements IMapper<License, LicenseDTO>{
    private final ModelMapper modelMapper;

    @Override
    public License toEntity(LicenseDTO d) {
        return modelMapper.map(d, License.class);
    }

    @Override
    public LicenseDTO toDTO(License e) {
        var permitDTO = modelMapper.map(e, LicenseDTO.class);
        permitDTO.setDriverId(e.getDriver().getDriverId());
        permitDTO.setLicenseType(e.getLicenseType().getCategory().name());

        return permitDTO;
    }

    @Override
    public LicenseDTO objToDTO(Object o) {
        return modelMapper.map(o, LicenseDTO.class);
    }

    @Override
    public License objToEntity(Object o) {
        return modelMapper.map(o, License.class);
    }
}
