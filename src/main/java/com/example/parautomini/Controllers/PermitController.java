package com.example.parautomini.Controllers;

import com.example.parautomini.DTOs.Requests.PermitReq;
import com.example.parautomini.DTOs.Response.PermitDTO;
import com.example.parautomini.Services.IPermitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("permits")
@RequiredArgsConstructor
public class PermitController {
    private final IPermitService permitService;

    @PostMapping("/{driverId}")
    public ResponseEntity<PermitDTO> add(@PathVariable int driverId, @RequestBody PermitReq permitReq) {
        return new ResponseEntity<>(permitService.add(driverId, permitReq), HttpStatus.CREATED);
    }
}
