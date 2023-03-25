package com.example.parautomini.DTOs.Response;

import com.example.parautomini.Enums.VehicleCategoryEnum;
import com.example.parautomini.Enums.VehicleTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleDTO {
    private String v_registrationNumber;
    private String name;
    private VehicleTypeEnum vehicleType;
}
