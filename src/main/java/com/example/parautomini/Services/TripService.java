package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Requests.TripReq;
import com.example.parautomini.DTOs.Requests.TripUpdateReq;
import com.example.parautomini.DTOs.Response.TripDTO;
import com.example.parautomini.Entites.Trip;
import com.example.parautomini.Exceptions.APIException;
import com.example.parautomini.Exceptions.ResourceNotFoundException;
import com.example.parautomini.Mappers.TripMapper;
import com.example.parautomini.Repositories.TripRepository;
import com.example.parautomini.Repositories.VehicleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService implements ITripService {

    private final TripRepository tripRepository;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final TripMapper tripMapper;

    @Override
    public List<TripDTO> getAll() {
        return tripRepository.findAll().stream().map(tripMapper::toDTO).toList();
    }

    @Override
    public TripDTO get(int tripId) {
        var trip = tripRepository.findById(tripId).orElseThrow(
                () -> new ResourceNotFoundException("Trip", "id", Integer.toString(tripId))
        );

        return tripMapper.toDTO(trip);
    }

    @Override
    public List<TripDTO> getAllUnassigned() {
        return tripRepository.findAllByIsAssigned(false).stream().map(tripMapper::toDTO).toList();
    }

    @Override
    public List<TripDTO> getAllAssigned() {
        return tripRepository.findAllByIsAssigned(true).stream().map(tripMapper::toDTO).toList();
    }

    @Override
    public List<TripDTO> getAllExpired() {
        return tripRepository.findAllExpired().stream().map(tripMapper::toDTO).toList();
    }

    @Override
    public void add(TripReq tripReq) {
        Date startDate = tripReq.getStartDate();
        Date endDate = tripReq.getEndDate();
        if(startDate.after(endDate)) {
            throw new APIException("Start date has to be before the end date of the trip", HttpStatus.BAD_REQUEST);
        }
        if(startDate.before(new Date())) {
            throw new APIException("Start date has to be in the future.", HttpStatus.BAD_REQUEST);
        }

        var v_type_req = tripReq.getVehicleType();
        var v_type = vehicleTypeRepository.findByLabel(v_type_req).orElseThrow(
                () -> new ResourceNotFoundException("Vehicle Type", "id", v_type_req)
        );

        var trip = tripMapper.objToEntity(tripReq);
        trip.setVehicleType(v_type);
        tripRepository.save(trip);
    }

    @Override
    public void delete(int tripId) {
        var trip = tripRepository.findById(tripId).orElseThrow(
                () -> new ResourceNotFoundException("Trip", "id", Integer.toString(tripId))
        );

        tripRepository.delete(trip);
    }

    @Override
    public void update(TripUpdateReq tripUpdateReq) {
        int tripId = tripUpdateReq.getTripId();
        var trip = tripRepository.findById(tripId).orElseThrow(
                () -> new ResourceNotFoundException("Trip", "id", Integer.toString(tripId))
        );
        if(trip.isAssigned()) {
            throw new APIException("You can't modify a trip that has been assigned/on-going", HttpStatus.BAD_REQUEST);
        }

        Date startDate = tripUpdateReq.getStartDate();
        Date endDate = tripUpdateReq.getEndDate();
        if(startDate.after(endDate)) {
            throw new APIException("Start date has to be before the end date of the trip", HttpStatus.BAD_REQUEST);
        }
        if(startDate.before(new Date())) {
            throw new APIException("Start date has to be in the future",HttpStatus.BAD_REQUEST);
        }

        var v_type_req = tripUpdateReq.getVehicleType();
        if(v_type_req != null) {
            var v_type = vehicleTypeRepository.findByLabel(v_type_req).orElseThrow(
                    () -> new ResourceNotFoundException("Vehicle Type", "id", v_type_req)
            );
            trip.setVehicleType(v_type);
        }
        trip.setEndDate(endDate);
        trip.setStartDate(startDate);

        tripRepository.save(trip);
    }
}
