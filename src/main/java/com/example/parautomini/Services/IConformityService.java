package com.example.parautomini.Services;

import com.example.parautomini.Entites.Driver;
import com.example.parautomini.Entites.Trip;
import com.example.parautomini.Entites.Vehicle;

import java.util.List;

public interface IConformityService {
    List<Driver> findMatchingDriversByTrip(Trip trip);
    List<Vehicle> findMatchingVehiclesByTrip(Trip trip);
}
