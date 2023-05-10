package com.example.parautomini.DTOs.Response;

import com.example.parautomini.Enums.VehicleTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AssignmentChoices {
    private int tripId;
    private VehicleTypeEnum vehicleType;
    private List<VehicleDTO> vehicles;
    private List<DriverAssignmentChoice> drivers;
}
