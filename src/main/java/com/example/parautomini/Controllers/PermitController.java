package com.example.parautomini.Controllers;

import com.example.parautomini.DTOs.Requests.PermitReq;
import com.example.parautomini.DTOs.Requests.PermitUpdateReq;
import com.example.parautomini.DTOs.Response.PermitDTO;
import com.example.parautomini.DTOs.Response.PermitTypeDTO;
import com.example.parautomini.DTOs.Response.Message;
import com.example.parautomini.Services.IPermitService;
import com.example.parautomini.Services.IPermitTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("permits")
@RequiredArgsConstructor
public class PermitController {
    private final IPermitService permitService;
    private final IPermitTypeService permitTypeService;

    @PostMapping
    public ResponseEntity<PermitDTO> add(@RequestBody PermitReq permitReq) {
        return new ResponseEntity<>(permitService.add(permitReq), HttpStatus.CREATED);
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<PermitDTO>> getAll(@PathVariable int driverId) {
        return ResponseEntity.ok(permitService.getAll(driverId));
    }

    @GetMapping("/{permitId}")
    public ResponseEntity<PermitDTO> get(@PathVariable int permitId) {
        return ResponseEntity.ok(permitService.get(permitId));
    }

    @GetMapping("/type/{permitTypeId}")
    public ResponseEntity<List<PermitDTO>> getAllByPermitType(@PathVariable int permitTypeId) {
        return ResponseEntity.ok(permitService.getAllByPermitType(permitTypeId));
    }

    @GetMapping("/driver/{driverId}/type/{permitTypeId}")
    public ResponseEntity<List<PermitDTO>> getAllByDriverAndType(@PathVariable int driverId, @PathVariable int permitTypeId) {
        return ResponseEntity.ok(permitService.getAllByDriverAndType(driverId, permitTypeId));
    }

    @PutMapping("/{permitId}")
    public ResponseEntity<PermitDTO> update(@PathVariable int permitId, @RequestBody PermitUpdateReq permitUpdateReq) {
        return ResponseEntity.ok(permitService.update(permitId, permitUpdateReq));
    }

    @DeleteMapping("/{permitId}")
    public ResponseEntity<Message> delete(@PathVariable int permitId) {
        permitService.delete(permitId);
        return ResponseEntity.ok(new Message("Permit deleted successfully", HttpStatus.OK));
    }

    @GetMapping("/types")
    public ResponseEntity<List<PermitTypeDTO>> getAllTypes() {
        return ResponseEntity.ok(permitTypeService.getAll());
    }

    @GetMapping("/types/{permitTypeId}")
    public ResponseEntity<PermitTypeDTO> getPermitType(@PathVariable int permitTypeId) {
        return ResponseEntity.ok(permitTypeService.get(permitTypeId));
    }
}
