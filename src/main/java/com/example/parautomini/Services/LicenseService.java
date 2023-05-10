package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Requests.LicenseReq;
import com.example.parautomini.DTOs.Requests.LicenseUpdateReq;
import com.example.parautomini.DTOs.Response.LicenseDTO;
import com.example.parautomini.Entites.Driver;
import com.example.parautomini.Entites.License;
import com.example.parautomini.Entites.LicenseType;
import com.example.parautomini.Exceptions.ResourceNotFoundException;
import com.example.parautomini.Mappers.LicenseMapper;
import com.example.parautomini.Repositories.DriverRepository;
import com.example.parautomini.Repositories.LicenseRepository;
import com.example.parautomini.Repositories.LicenseTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LicenseService implements ILicenseService {
    private final LicenseRepository licenseRepository;
    private final LicenseTypeRepository licenseTypeRepository;
    private final DriverRepository driverRepository;
    private final LicenseMapper licenseMapper;

    public LicenseDTO add(LicenseReq licenseReq) {
        int permitTypeId = licenseReq.getLicenseTypeId();
        var driver = this.findDriver(licenseReq.getDriverId());
        var permitType = this.findPermitType(permitTypeId);

        var permit = licenseMapper.objToEntity(licenseReq);
        permit.setLicenseType(permitType);
        permit.setDeliveryDate(licenseReq.getDeliveryDate());
        permit.setDriver(driver);

        return licenseMapper.toDTO(licenseRepository.save(permit));
    }

    public LicenseDTO get(int licenseId) {
        var permit = this.findLicense(licenseId);
        return licenseMapper.toDTO(permit);
    }

    public List<LicenseDTO> getAll(int driverId) {
        var driver = this.findDriver(driverId);
        return licenseRepository.findAllByDriverId(driver).stream().map(licenseMapper::toDTO).toList();
    }

    @Override
    public List<LicenseDTO> getAllByPermitType(int licenseTypeId) {
        return licenseRepository.findAllByLicenseTypeId(licenseTypeId).stream().map(licenseMapper::toDTO).toList();
    }

    @Override
    public LicenseDTO update(LicenseUpdateReq licenseUpdateReq) {
        int licenseId = licenseUpdateReq.getLicenseId();
        var permit = this.findLicense(licenseId);
        int licenseTypeId = licenseUpdateReq.getLicenseTypeId();
        Date delivery = licenseUpdateReq.getDeliveryDate();

        if(licenseTypeId > 0) {
            var permitType = this.findPermitType(licenseTypeId);
            permit.setLicenseType(permitType);
        }
        if(delivery != null)
        {
            permit.setDeliveryDate(delivery);
        }

        return licenseMapper.toDTO(licenseRepository.save(permit));
    }

    @Override
    public void delete(int licenseId) {
        var permit = this.findLicense(licenseId);
        licenseRepository.delete(permit);
    }

    @Override
    public List<LicenseDTO> getAllByDriverAndType(int driverId, int permitTypeId) {
        return licenseRepository.findAllByDriverAndType(driverId, permitTypeId).stream().map(licenseMapper::toDTO).toList();
    }

    private Driver findDriver(int driverId) {
        return driverRepository.findByDriverId(driverId).orElseThrow(
                () -> new ResourceNotFoundException("Driver", "id", Integer.toString(driverId))
        );
    }

    private License findLicense(int licenseId) {
        return licenseRepository.findByLicenseId(licenseId).orElseThrow(
                () -> new ResourceNotFoundException("Permit", "id", Integer.toString(licenseId))
        );
    }

    private LicenseType findPermitType(int permitTypeId) {
        return licenseTypeRepository.findByLicenseTypeId(permitTypeId).orElseThrow(
                () -> new ResourceNotFoundException("Permit type", "id", Integer.toString(permitTypeId))
        );
    }
}
