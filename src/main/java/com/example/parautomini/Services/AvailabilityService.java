package com.example.parautomini.Services;

import com.example.parautomini.Entites.Driver;
import com.example.parautomini.Entites.Vehicle;
import com.example.parautomini.Repositories.AssignmentRepository;
import com.example.parautomini.Repositories.HolidayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AvailabilityService implements IAvailabilityService {
    private final AssignmentRepository assignmentRepository;
    private final HolidayRepository holidayRepository;

    @Override
    public boolean isVehicleAvailable(Vehicle vehicle, Date startDate, Date endDate) {
        if(assignmentRepository.IsVehicleAvailableForTrip(vehicle, startDate, endDate)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isDriverAvailable(Driver driver, Date startDate, Date endDate) {
        System.out.println(assignmentRepository.IsDriverAvailableForTrip(driver, startDate, endDate));
        System.out.println(holidayRepository.IsDriverAvailableForTrip(driver, startDate, endDate));
        if(assignmentRepository.IsDriverAvailableForTrip(driver, startDate, endDate) && holidayRepository.IsDriverAvailableForTrip(driver, startDate, endDate)) {
            return true;
        }

        return false;
    }
}
