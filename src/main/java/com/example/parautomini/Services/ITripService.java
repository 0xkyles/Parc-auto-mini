package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Requests.TripReq;
import com.example.parautomini.DTOs.Requests.TripUpdateReq;
import com.example.parautomini.DTOs.Response.TripDTO;

import java.util.List;

public interface ITripService {
    List<TripDTO> getAll();
    TripDTO get(int tripId);

    List<TripDTO> getAllUnassigned();
    List<TripDTO> getAllAssigned();

    List<TripDTO> getAllExpired();

    void add(TripReq tripReq);

    void delete(int tripId);

    void update(TripUpdateReq tripUpdateReq);
}
