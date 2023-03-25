package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Requests.DriverReq;
import com.example.parautomini.DTOs.Response.DriverDTO;
import com.example.parautomini.Entites.Driver;
import com.example.parautomini.Exceptions.ResourceNotFoundException;
import com.example.parautomini.Mappers.DriverMapper;
import com.example.parautomini.Repositories.DriverRepository;
import com.example.parautomini.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService implements IDriverService {
    private final DriverRepository driverRepository;
    private final UserRepository userRepository;
    private final DriverMapper driverMapper;

    @Override
    public DriverDTO add(DriverReq driverReq) {
        int userId = driverReq.getUserId();
        var user = userRepository.findByUserId(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", Integer.toString(userId))
        );
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

    private Driver findDriver(int driverId) {
        return driverRepository.findById(driverId).orElseThrow(
                () -> new ResourceNotFoundException("Driver", "id", Integer.toString(driverId))
        );
    }
}
