package com.example.parautomini.Controllers;

import com.example.parautomini.DTOs.Requests.HolidayReqDTO;
import com.example.parautomini.DTOs.Requests.HolidayUpdateReq;
import com.example.parautomini.DTOs.Response.HolidayDTO;
import com.example.parautomini.DTOs.Response.Message;
import com.example.parautomini.Services.IHolidayService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/holidays")
@RequiredArgsConstructor
public class HolidayController {
    private final IHolidayService holidayService;

    @GetMapping
    public ResponseEntity<List<HolidayDTO>> getAll() {
        return ResponseEntity.ok(holidayService.getAll());
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<HolidayDTO>> getByDriverId(@PathVariable int driverId) {
        return ResponseEntity.ok(holidayService.get(driverId));
    }

    @PostMapping
    public ResponseEntity<Message> addHoliday(@RequestBody @Valid HolidayReqDTO holidayReqDTO){
        holidayService.add(holidayReqDTO);
        return new ResponseEntity<>(new Message("Holiday has been added successfully"), HttpStatus.CREATED);
    }

    @DeleteMapping("/{holidayId}")
    public ResponseEntity<Message> deleteHoliday(@PathVariable int holidayId) {
        holidayService.delete(holidayId);
        return new ResponseEntity<>(new Message("Holiday has been deleted successfully"), HttpStatus.OK);
    }

    @DeleteMapping("/driver/{driverId}")
    public ResponseEntity<Message> deleteAllHolidays(@PathVariable int driverId) {
        holidayService.deleteAll(driverId);
        return new ResponseEntity<>(new Message("All Holiday has been deleted successfully for ID: " + driverId), HttpStatus.OK);
    }

    @PutMapping("/{holidayId}")
    public ResponseEntity<Message> updateHoliday(@PathVariable int holidayId, @RequestBody @Valid HolidayUpdateReq holiday){
        holidayService.update(holidayId, holiday);
        return new ResponseEntity<>(new Message("Holiday has been updated successfully"),HttpStatus.OK);
    }
}
