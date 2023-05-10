package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Requests.AssignmentReqDTO;
import com.example.parautomini.DTOs.Response.AssignmentChoices;
import com.example.parautomini.DTOs.Response.AssignmentDTO;

import java.util.List;

public interface IAssignmentService {
    List<AssignmentDTO> getAll();
    AssignmentDTO get(int assignmentId);
    List<AssignmentDTO> getAllByDriver(int driverId);
    AssignmentDTO getByTrip(int tripId);
    List<AssignmentDTO> getAllByVehicle(String v_registrationNumber);
    List<AssignmentDTO> getAllByVehicleType(String vehicleType);
    List<AssignmentDTO> getAllByStatus(String status);
    AssignmentChoices getTripChoices(int tripId);
    void addAssignment(AssignmentReqDTO assignmentReqDTO);
}
