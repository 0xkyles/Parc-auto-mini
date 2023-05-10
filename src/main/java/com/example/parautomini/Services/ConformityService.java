package com.example.parautomini.Services;

import com.example.parautomini.Entites.*;
import com.example.parautomini.Enums.VehicleTypeEnum;
import com.example.parautomini.Repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConformityService implements IConformityService {
    private final VehicleRepository vehicleRepository;
    private final LicenseRepository licenseRepository;

    @Override
    public List<Driver> findMatchingDriversByTrip(Trip trip) {
        VehicleType vehicleType = trip.getVehicleType();

        return licenseRepository.findAllowedDriversByVehicleType(vehicleType);
    }

    @Override
    public List<Vehicle> findMatchingVehiclesByTrip(Trip trip) {
        VehicleType vehicleType = trip.getVehicleType();

        return vehicleRepository.findAllByVehicleType(vehicleType);
    }
}
