package com.example.parautomini.DTOs.Response;

import com.example.parautomini.Enums.VehicleTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DriverAssignmentChoice {
    private int driverId;
    private String firstName;
    private String lastName;
    private String registrationNumber;
    private String cin;
    private List<VehicleTypeEnum> permittedToDriveVehicleTypes;
}
