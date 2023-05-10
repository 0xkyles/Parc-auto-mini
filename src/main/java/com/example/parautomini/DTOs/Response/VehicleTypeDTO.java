package com.example.parautomini.DTOs.Response;

import com.example.parautomini.Enums.VehicleCategoryEnum;
import com.example.parautomini.Enums.VehicleTypeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleTypeDTO {
    private int vehicleTypeId;
    private VehicleTypeEnum label;
    private VehicleCategoryEnum vehicleCategory;
    private String description;
}
