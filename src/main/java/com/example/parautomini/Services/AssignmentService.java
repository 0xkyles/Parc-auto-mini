package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Requests.AssignmentReqDTO;
import com.example.parautomini.DTOs.Response.AssignmentChoices;
import com.example.parautomini.DTOs.Response.AssignmentDTO;
import com.example.parautomini.DTOs.Response.DriverAssignmentChoice;
import com.example.parautomini.DTOs.Response.VehicleDTO;
import com.example.parautomini.Entites.Assignment;
import com.example.parautomini.Entites.Driver;
import com.example.parautomini.Entites.Trip;
import com.example.parautomini.Entites.Vehicle;
import com.example.parautomini.Exceptions.APIException;
import com.example.parautomini.Exceptions.ResourceNotFoundException;
import com.example.parautomini.Mappers.AssignmentMapper;
import com.example.parautomini.Mappers.VehicleMapper;
import com.example.parautomini.Repositories.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentService implements IAssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final DriverRepository driverRepository;
    private final TripRepository tripRepository;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final VehicleRepository vehicleRepository;
    private final AssignmentMapper mapper;
    private final VehicleMapper vehicleMapper;
    private final VehicleLicenseTypeMappingRepository vehicleLicenseTypeMappingRepository;
    private final ModelMapper modelMapper;
    private final IConformityService conformityService;
    private final IAvailabilityService availabilityService;

    @Override
    public List<AssignmentDTO> getAll() {
        return assignmentRepository.findAll().stream().map(mapper::toDTO).toList();
    }

    public AssignmentDTO get(int assignmentId) {
        var assignment = assignmentRepository.findById(assignmentId).orElseThrow(
                () -> new ResourceNotFoundException("Assignment", "ID", Integer.toString(assignmentId))
        );

        return mapper.toDTO(assignment);
    }

    @Override
    public List<AssignmentDTO> getAllByDriver(int driverId) {
        var driver = this.findDriver(driverId);

        return assignmentRepository.findAllByDriver(driver).stream().map(mapper::toDTO).toList();
    }

    @Override
    public AssignmentDTO getByTrip(int tripId) {
        var trip = this.findTrip(tripId);
        if(!trip.isAssigned()) throw new APIException("Trip is not assigned.", HttpStatus.BAD_REQUEST);

        return mapper.toDTO(assignmentRepository.findByTrip(trip).get());
    }

    @Override
    public List<AssignmentDTO> getAllByVehicle(String v_registrationNumber) {
        var vehicle = this.findVehicle(v_registrationNumber);

        return assignmentRepository.findAllByVehicle(vehicle).stream().map(mapper::toDTO).toList();
    }

    @Override
    public List<AssignmentDTO> getAllByVehicleType(String vehicleType) {
        var type = vehicleTypeRepository.findByLabel(vehicleType).orElseThrow(
                () -> new ResourceNotFoundException("Vehicle type", "ID", vehicleType)
        );

        return assignmentRepository.findAllByVehicleType(type).stream().map(mapper::toDTO).toList();
    }

    @Override
    public List<AssignmentDTO> getAllByStatus(String status) {
        Assignment.AssignmentStatus s;
        switch(status) {
            case "terminated":
                s = Assignment.AssignmentStatus.TERMINATED;
                break;
            case "scheduled":
                s = Assignment.AssignmentStatus.SCHEDULED;
                break;
            case "ongoing":
                s = Assignment.AssignmentStatus.ONGOING;
                break;
            default:
                throw new ResourceNotFoundException("Status", "name", status);
        }

        return assignmentRepository.findAllByStatus(s).stream().map(mapper::toDTO).toList();
    }

    private Trip findTrip(int tripId) {
        return tripRepository.findById(tripId).orElseThrow(
                () -> new ResourceNotFoundException("Trip", "ID", Integer.toString(tripId))
        );
    }

    private Vehicle findVehicle(String v_registrationNumber) {
        return vehicleRepository.findById(v_registrationNumber).orElseThrow(
                () -> new ResourceNotFoundException("Vehicle", "ID", v_registrationNumber)
        );
    }

    private Driver findDriver(int driverId) {
        return driverRepository.findById(driverId).orElseThrow(
                () -> new ResourceNotFoundException("Driver", "ID", Integer.toString(driverId))
        );
    }

    @Override
    public AssignmentChoices getTripChoices(int tripId) {
        var trip = this.findTrip(tripId);
        verifyEligibilityOfTrip(trip);

        List<Vehicle> vehicles = conformityService.findMatchingVehiclesByTrip(trip);
        List<Driver> drivers = conformityService.findMatchingDriversByTrip(trip);
        Date startDate = trip.getStartDate();
        Date endDate = trip.getEndDate();

        var choices = new AssignmentChoices();
        var vehicleOptions = new ArrayList<VehicleDTO>();
        var driverOptions = new ArrayList<DriverAssignmentChoice>();

        for (var driver : drivers) {
            if(availabilityService.isDriverAvailable(driver, startDate, endDate)) {
                var d = modelMapper.map(driver, DriverAssignmentChoice.class);
                var v_types = vehicleLicenseTypeMappingRepository.findAllowedToDriveVehicleTypesByDriver(driver);
                d.setPermittedToDriveVehicleTypes(v_types.stream().map((type) -> type.getLabel()).toList());

                driverOptions.add(d);
            }
        }

        for (var vehicle : vehicles) {
            if(availabilityService.isVehicleAvailable(vehicle, startDate, endDate)) {
                vehicleOptions.add(vehicleMapper.toDTO(vehicle));
            }
        }

        choices.setTripId(tripId);
        choices.setVehicleType(trip.getVehicleType().getLabel());
        choices.setVehicles(vehicleOptions);
        choices.setDrivers(driverOptions);
        return choices;
    }

    private void verifyEligibilityOfTrip(Trip trip) {
        if(trip.isAssigned()) {
            throw new APIException("Trip has already been assigned", HttpStatus.BAD_REQUEST);
        }

        if(trip.getStartDate().before(new Date())) {
            throw new APIException("Trip has already expired", HttpStatus.BAD_REQUEST);
        }
    }

    private void verifyEligibilityOfDriver(Driver driver, Trip trip) {
        List<Driver> drivers = conformityService.findMatchingDriversByTrip(trip);
        if(!drivers.contains(driver)) throw new APIException("Driver is not qualified for this trip", HttpStatus.BAD_REQUEST);

        if(!availabilityService.isDriverAvailable(driver, trip.getStartDate(), trip.getEndDate())) throw new APIException("Driver is not available for this trip", HttpStatus.BAD_REQUEST);
    }

    private void verifyEligibilityOfVehicle(Vehicle vehicle, Trip trip) {
        List<Vehicle> vehicles = conformityService.findMatchingVehiclesByTrip(trip);
        if(!vehicles.contains(vehicle)) throw new APIException("Vehicle is not qualified for this trip (not the same type)", HttpStatus.BAD_REQUEST);

        if(!availabilityService.isVehicleAvailable(vehicle, trip.getStartDate(), trip.getEndDate())) throw new APIException("Vehicle is not available for this trip", HttpStatus.BAD_REQUEST);
    }

    @Override
    public void addAssignment(AssignmentReqDTO assignmentReqDTO) {
        var driver = this.findDriver(assignmentReqDTO.getDriverId());
        var vehicle = this.findVehicle(assignmentReqDTO.getV_registrationNumber());
        var trip = this.findTrip(assignmentReqDTO.getTripId());

        verifyEligibilityOfTrip(trip);
        verifyEligibilityOfDriver(driver, trip);
        verifyEligibilityOfVehicle(vehicle, trip);

        var assignment = new Assignment();
        assignment.setTrip(trip);
        assignment.setDriver(driver);
        assignment.setVehicle(vehicle);
        assignment.setAffectationDate(new Date());
        assignment.setStatus(Assignment.AssignmentStatus.SCHEDULED);
        assignmentRepository.save(assignment);

        trip.setAssigned(true);
        tripRepository.save(trip);
    }
}
