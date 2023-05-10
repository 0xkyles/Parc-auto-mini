package com.example.parautomini.Controllers;

import com.example.parautomini.DTOs.Requests.LicenseReq;
import com.example.parautomini.DTOs.Requests.LicenseUpdateReq;
import com.example.parautomini.DTOs.Response.LicenseDTO;
import com.example.parautomini.DTOs.Response.LicenseTypeDTO;
import com.example.parautomini.DTOs.Response.Message;
import com.example.parautomini.Services.ILicenseService;
import com.example.parautomini.Services.ILicenseTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("licenses")
@RequiredArgsConstructor
public class LicenseController {
    private final ILicenseService licenseService;
    private final ILicenseTypeService licenseTypeService;

    @PostMapping
    public ResponseEntity<LicenseDTO> add(@RequestBody @Valid LicenseReq licenseReq) {
        return new ResponseEntity<>(licenseService.add(licenseReq), HttpStatus.CREATED);
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<LicenseDTO>> getAll(@PathVariable int driverId) {
        return ResponseEntity.ok(licenseService.getAll(driverId));
    }

    @GetMapping("/{licenseId}")
    public ResponseEntity<LicenseDTO> get(@PathVariable int licenseId) {
        return ResponseEntity.ok(licenseService.get(licenseId));
    }

    @GetMapping("/type/{licenseTypeId}")
    public ResponseEntity<List<LicenseDTO>> getAllByPermitType(@PathVariable int licenseTypeId) {
        return ResponseEntity.ok(licenseService.getAllByPermitType(licenseTypeId));
    }

    @GetMapping("/driver/{driverId}/type/{licenseTypeId}")
    public ResponseEntity<List<LicenseDTO>> getAllByDriverAndType(@PathVariable int driverId, @PathVariable int licenseTypeId) {
        return ResponseEntity.ok(licenseService.getAllByDriverAndType(driverId, licenseTypeId));
    }

    @PutMapping
    public ResponseEntity<LicenseDTO> update(@RequestBody @Valid LicenseUpdateReq licenseUpdateReq) {
        return ResponseEntity.ok(licenseService.update(licenseUpdateReq));
    }

    @DeleteMapping("/{licenseId}")
    public ResponseEntity<Message> delete(@PathVariable int licenseId) {
        licenseService.delete(licenseId);
        return ResponseEntity.ok(new Message("Licence deleted successfully"));
    }

    @GetMapping("/types")
    public ResponseEntity<List<LicenseTypeDTO>> getAllTypes() {
        return ResponseEntity.ok(licenseTypeService.getAll());
    }

    @GetMapping("/types/{licenseTypeId}")
    public ResponseEntity<LicenseTypeDTO> getPermitType(@PathVariable int licenseTypeId) {
        return ResponseEntity.ok(licenseTypeService.get(licenseTypeId));
    }
}
