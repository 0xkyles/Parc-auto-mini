package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Requests.DriverReq;
import com.example.parautomini.DTOs.Response.DriverDTO;
import com.example.parautomini.Entites.Driver;
import com.example.parautomini.Enums.LicenseTypeEnum;
import com.example.parautomini.Enums.VehicleTypeEnum;
import com.example.parautomini.Exceptions.APIException;
import com.example.parautomini.Exceptions.ResourceNotFoundException;
import com.example.parautomini.Mappers.DriverMapper;
import com.example.parautomini.Repositories.*;
import com.example.parautomini.auth.IAuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService implements IDriverService {
    private final DriverRepository driverRepository;
    private final LicenseRepository licenseRepository;
    private final LicenseTypeRepository licenseTypeRepository;
    private final UserRepository userRepository;
    private final VehicleLicenseTypeMappingRepository vehicleLicenseTypeMappingRepository;
    private final DriverMapper driverMapper;
    private final IAuthenticationFacade authenticationFacade;

    @Override
    public DriverDTO add(DriverReq driverReq) {
        int userId = driverReq.getUserId();

        var user = userRepository.findByUserId(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", Integer.toString(userId))
        );
        if(driverRepository.existsByUser(user)) {
            throw new APIException("User has already an associated driver to it", HttpStatus.BAD_REQUEST);
        }
        var driver = driverMapper.objToEntity(driverReq);
        driver.setUser(user);
        return driverMapper.toDTO(driverRepository.save(driver));
    }

    @Override
    public DriverDTO get(int driverId) {
        var driver = driverRepository.findById(driverId).orElseThrow(
                () -> new ResourceNotFoundException("Driver", "id", Integer.toString(driverId))
        );

        return driverMapper.toDTO(driver);
    }

    @Override
    public List<DriverDTO> getAll() {
        return driverRepository.findAll().stream().map(driverMapper::toDTO).toList();
    }

    @Override
    public DriverDTO update(int driverId, DriverReq driverReq) {
        var driver = this.findDriver(driverId);
        String registration = driverReq.getRegistrationNumber();
        String firstName = driverReq.getFirstName();
        String lastName = driverReq.getLastName();
        String CIN = driverReq.getCin();
        Date bd = driverReq.getBirthday();

        if(registration != null)
            driver.setRegistrationNumber(registration);
        if(firstName != null)
            driver.setFirstName(firstName);
        if(lastName != null)
            driver.setLastName(lastName);
        if(CIN != null)
            driver.setCin(CIN);
        if(bd != null)
            driver.setBirthday(bd);

        return driverMapper.toDTO(driverRepository.save(driver));
    }

    @Override
    public void delete(int driverId) {
        var driver = this.findDriver(driverId);
        driverRepository.delete(driver);
    }

    @Override
    public List<DriverDTO> getAllDriversWithLicenseType(int licenseTypeId) {
        var licenseType = licenseTypeRepository.findByLicenseTypeId(licenseTypeId).orElseThrow(
                () -> new ResourceNotFoundException("License", "ID", Integer.toString(licenseTypeId))
        );

        return licenseRepository.findDriversByLicenseType(licenseType.getCategory()).stream().map(driverMapper::toDTO).toList();
    }

    @Override
    public List<LicenseTypeEnum> getAllLicenseTypesOfDriver(int driverId) {
        var driver = this.findDriver(driverId);

        var l_types = licenseRepository.findDistinctLicenseTypesByDriver(driver);
        return l_types.stream().map((type) -> type.getCategory()).toList();
    }

    @Override
    public List<VehicleTypeEnum> getAllVehicleTypesOfDriver(int driverId) {
        var driver = this.findDriver(driverId);

        var v_types = vehicleLicenseTypeMappingRepository.findAllowedToDriveVehicleTypesByDriver(driver);
        return v_types.stream().map((type) -> type.getLabel()).toList();
    }

    private Driver findDriver(int driverId) {
        return driverRepository.findById(driverId).orElseThrow(
                () -> new ResourceNotFoundException("Driver", "id", Integer.toString(driverId))
        );
    }
}
