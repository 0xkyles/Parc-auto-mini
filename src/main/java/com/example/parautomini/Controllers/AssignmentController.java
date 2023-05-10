package com.example.parautomini.Controllers;

import com.example.parautomini.DTOs.Requests.AssignmentReqDTO;
import com.example.parautomini.DTOs.Response.AssignmentChoices;
import com.example.parautomini.DTOs.Response.AssignmentDTO;
import com.example.parautomini.DTOs.Response.Message;
import com.example.parautomini.Exceptions.ResourceNotFoundException;
import com.example.parautomini.Repositories.TripRepository;
import com.example.parautomini.Services.IAssignmentService;
import com.example.parautomini.Services.IConformityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignments")
@RequiredArgsConstructor
public class AssignmentController {
    private final IAssignmentService assignmentService;
    private final TripRepository tripRepository;

    @GetMapping
    public ResponseEntity<List<AssignmentDTO>> getAll() {
        return ResponseEntity.ok(assignmentService.getAll());
    }

    @GetMapping("/{assignmentId}")
    public ResponseEntity<AssignmentDTO> get(@PathVariable int assignmentId) {
        return ResponseEntity.ok(assignmentService.get(assignmentId));
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<AssignmentDTO>> getAllByDriver(@PathVariable int driverId) {
        return ResponseEntity.ok(assignmentService.getAllByDriver(driverId));
    }

    @GetMapping("/trip/{tripId}")
    public ResponseEntity<AssignmentDTO> getByTrip(@PathVariable int tripId) {
        return ResponseEntity.ok(assignmentService.getByTrip(tripId));
    }

    @GetMapping("/vehicle/{v_registrationNumber}")
    public ResponseEntity<List<AssignmentDTO>> getAllAssignmentsByVehicle(@PathVariable String v_registrationNumber) {
        return ResponseEntity.ok(assignmentService.getAllByVehicle(v_registrationNumber));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<AssignmentDTO>> getAllByStatus(@PathVariable String status) {
        return ResponseEntity.ok(assignmentService.getAllByStatus(status));
    }

    @GetMapping("/vehicle/type/{vehicleType}")
    public ResponseEntity<List<AssignmentDTO>> getAllAssignmentsByVehicleType(@PathVariable String vehicleType) {
        return ResponseEntity.ok(assignmentService.getAllByVehicleType(vehicleType));
    }

    @PostMapping
    public ResponseEntity<Message> addAssignment(@RequestBody AssignmentReqDTO assignmentReqDTO) {
        assignmentService.addAssignment(assignmentReqDTO);
        return ResponseEntity.ok(new Message("Assignment has been added for trip ID : " + assignmentReqDTO.getTripId()));
    }

    @GetMapping("/choice/{tripId}")
    public ResponseEntity<AssignmentChoices> getTripChoices(@PathVariable int tripId) {
        return ResponseEntity.ok(assignmentService.getTripChoices(tripId));
    }
}
