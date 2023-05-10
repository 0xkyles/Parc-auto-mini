package com.example.parautomini.Controllers;

import com.example.parautomini.DTOs.Requests.TripReq;
import com.example.parautomini.DTOs.Requests.TripUpdateReq;
import com.example.parautomini.DTOs.Response.Message;
import com.example.parautomini.DTOs.Response.TripDTO;
import com.example.parautomini.Services.ITripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {
    private final ITripService tripService;

    @PostMapping
    public ResponseEntity<Message> add(@RequestBody TripReq tripReq) {
        tripService.add(tripReq);
        return new ResponseEntity<>(new Message("Trip added successfully"), HttpStatus.CREATED);
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<Message> delete(@PathVariable int tripId) {
        tripService.delete(tripId);
        return ResponseEntity.ok(new Message("Trip deleted successfully"));
    }

    @PutMapping
    public ResponseEntity<Message> update(@RequestBody TripUpdateReq tripUpdateReq) {
        tripService.update(tripUpdateReq);
        return ResponseEntity.ok(new Message("Trip updated successfully"));
    }

    @GetMapping
    public ResponseEntity<List<TripDTO>> getAll() {
        return ResponseEntity.ok(tripService.getAll());
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<TripDTO> getById(@PathVariable int tripId) {
        return ResponseEntity.ok(tripService.get(tripId));
    }

    @GetMapping("/status/unassigned")
    public ResponseEntity<List<TripDTO>> getAllUnassigned() {
        return ResponseEntity.ok(tripService.getAllUnassigned());
    }

    @GetMapping("/status/assigned")
    public ResponseEntity<List<TripDTO>> getAllAssigned() {
        return ResponseEntity.ok(tripService.getAllAssigned());
    }

    @GetMapping("/status/expired")
    public ResponseEntity<List<TripDTO>> getAllExpired() {
        return ResponseEntity.ok(tripService.getAllExpired());
    }
}
