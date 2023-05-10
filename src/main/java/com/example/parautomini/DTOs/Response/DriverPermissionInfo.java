package com.example.parautomini.DTOs.Response;

import com.example.parautomini.Enums.LicenseTypeEnum;
import com.example.parautomini.Enums.VehicleTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DriverPermissionInfo {
    private int driverId;
    private List<LicenseTypeEnum> licenseTypes;
    private List<VehicleTypeEnum> permittedToDriveVehicleTypes;
}
