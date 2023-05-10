package com.example.parautomini.Services;

import com.example.parautomini.Entites.Driver;
import com.example.parautomini.Entites.Vehicle;

import java.util.Date;

public interface  IAvailabilityService {
    boolean isVehicleAvailable(Vehicle vehicle, Date startDate, Date endDate);
    boolean isDriverAvailable(Driver driver, Date startDate, Date endDate);
}
