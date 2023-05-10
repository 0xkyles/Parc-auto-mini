package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Requests.LicenseTypeUpdateReq;
import com.example.parautomini.DTOs.Response.LicenseTypeDTO;
import com.example.parautomini.Entites.LicenseType;
import com.example.parautomini.Exceptions.ResourceNotFoundException;
import com.example.parautomini.Mappers.LicenseTypeMapper;
import com.example.parautomini.Repositories.LicenseTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LicenseTypeService implements ILicenseTypeService {
    private final LicenseTypeRepository licenseTypeRepository;
    private final LicenseTypeMapper licenseTypeMapper;

    @Override
    public List<LicenseTypeDTO> getAll() {
        return licenseTypeRepository.findAll().stream().map(licenseTypeMapper::toDTO).toList();
    }

    @Override
    public LicenseTypeDTO get(int licenseTypeId) {
        return licenseTypeMapper.toDTO(this.findByLicenseTypeId(licenseTypeId));
    }

    @Override
    public LicenseTypeDTO update(int permitTypeId, LicenseTypeUpdateReq req) {
        return null;
    }

    private LicenseType findByLicenseTypeId(int licenseTypeId) {
        return licenseTypeRepository.findByLicenseTypeId(licenseTypeId).orElseThrow(
                () -> new ResourceNotFoundException("License type", "id", Integer.toString(licenseTypeId))
        );
    }
}
