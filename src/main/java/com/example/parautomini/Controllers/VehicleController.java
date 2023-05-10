package com.example.parautomini.Controllers;

import com.example.parautomini.DTOs.Requests.VehicleReq;
import com.example.parautomini.DTOs.Response.Message;
import com.example.parautomini.DTOs.Response.VehicleCategoryDTO;
import com.example.parautomini.DTOs.Response.VehicleDTO;
import com.example.parautomini.DTOs.Response.VehicleTypeDTO;
import com.example.parautomini.Services.IVehicleCategoryService;
import com.example.parautomini.Services.IVehicleService;
import com.example.parautomini.Services.IVehicleTypeService;
import com.example.parautomini.Services.VehicleCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private final IVehicleService vehicleService;
    private final IVehicleTypeService vehicleTypeService;
    private final IVehicleCategoryService vehicleCategoryService;

    @GetMapping("/{v_registrationNumber}")
    public ResponseEntity<VehicleDTO> get(@PathVariable String v_registrationNumber) {
        return ResponseEntity.ok(vehicleService.getById(v_registrationNumber));
    }

    @PostMapping
    public ResponseEntity<VehicleDTO> add(@RequestBody @Valid VehicleReq req) {
        return new ResponseEntity(vehicleService.add(req), HttpStatus.CREATED);
    }

    @PutMapping("/{v_registrationNumber}")
    public ResponseEntity<VehicleDTO> update(@PathVariable String v_registrationNumber, @RequestBody VehicleReq req) {
        return ResponseEntity.ok(vehicleService.update(v_registrationNumber, req));
    }

    @DeleteMapping("/{v_registrationNumber}")
    public ResponseEntity<Message> delete(@PathVariable String v_registrationNumber) {
        vehicleService.delete(v_registrationNumber);
        return ResponseEntity.ok(new Message("Vehicle " + v_registrationNumber + " has been deleted successfully"));
    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAll() {
        return ResponseEntity.ok(vehicleService.getAll());
    }

    @GetMapping("/type/{vehicleType}")
    public ResponseEntity<List<VehicleDTO>> getAllByType(@PathVariable String vehicleType) {
        return ResponseEntity.ok(vehicleService.getAllByVehicleType(vehicleType));
    }

    @GetMapping("/types")
    public ResponseEntity<List<VehicleTypeDTO>> getAllVehicleTypes() {
        return ResponseEntity.ok(vehicleTypeService.getAll());
    }

    @GetMapping("/categories")
    public ResponseEntity<List<VehicleCategoryDTO>> getAllVehicleCategories() {
        return ResponseEntity.ok(vehicleCategoryService.getAll());
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<VehicleCategoryDTO> getAllVehicleCategories(@PathVariable String categoryId) {
        return ResponseEntity.ok(vehicleCategoryService.get(categoryId));
    }

    @GetMapping("/types/{vehicleType}")
    public ResponseEntity<VehicleTypeDTO> getVehicleTypeById(@PathVariable String vehicleType) {
        return ResponseEntity.ok(vehicleTypeService.get(vehicleType));
    }

    @GetMapping("/types/category/{vehicleCategory}")
    public ResponseEntity<List<VehicleTypeDTO>> getVehicleTypeByCategory(@PathVariable String vehicleCategory) {
        return ResponseEntity.ok(vehicleTypeService.getByCategory(vehicleCategory));
    }
}
