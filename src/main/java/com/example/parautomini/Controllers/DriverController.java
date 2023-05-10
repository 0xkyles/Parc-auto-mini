package com.example.parautomini.Controllers;

import com.example.parautomini.DTOs.Requests.DriverReq;
import com.example.parautomini.DTOs.Response.DriverDTO;
import com.example.parautomini.DTOs.Response.DriverPermissionInfo;
import com.example.parautomini.DTOs.Response.Message;
import com.example.parautomini.Enums.LicenseTypeEnum;
import com.example.parautomini.Services.IDriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("drivers")
@RequiredArgsConstructor
public class DriverController {
    private final IDriverService driverService;

    @PostMapping
    public ResponseEntity<DriverDTO> add(@RequestBody @Valid DriverReq driver) {
        return new ResponseEntity<>(driverService.add(driver), HttpStatus.CREATED);
    }

    @GetMapping("/{driverId}")
    public ResponseEntity<DriverDTO> get(@PathVariable int driverId) {
        return ResponseEntity.ok(driverService.get(driverId));
    }

    @GetMapping
    public ResponseEntity<List<DriverDTO>> getAll() {
        return ResponseEntity.ok(driverService.getAll());
    }

    @GetMapping("/licenseType/{licenseTypeId}")
    public ResponseEntity<List<DriverDTO>> getAllDriversWithLicenseType(@PathVariable int licenseTypeId) {
        return ResponseEntity.ok(driverService.getAllDriversWithLicenseType(licenseTypeId));
    }

    @GetMapping("/info/{driverId}")
    public ResponseEntity<DriverPermissionInfo> getAllLicenseTypesOfDriver(@PathVariable int driverId) {
        var res = new DriverPermissionInfo();
        res.setDriverId(driverId);
        res.setLicenseTypes(driverService.getAllLicenseTypesOfDriver(driverId));
        res.setPermittedToDriveVehicleTypes(driverService.getAllVehicleTypesOfDriver(driverId));

        return ResponseEntity.ok(res);
    }

    @PutMapping("/{driverId}")
    public ResponseEntity<DriverDTO> update(@PathVariable int driverId, @RequestBody DriverReq driver) {
        return ResponseEntity.ok(driverService.update(driverId, driver));
    }

    @DeleteMapping("/{driverId}")
    public ResponseEntity<Message> delete(@PathVariable int driverId) {
        driverService.delete(driverId);
        return ResponseEntity.ok(new Message("User deleted successfully"));
    }
}
