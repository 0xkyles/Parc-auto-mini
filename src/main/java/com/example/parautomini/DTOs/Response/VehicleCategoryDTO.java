package com.example.parautomini.DTOs.Response;

import com.example.parautomini.Enums.VehicleCategoryEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleCategoryDTO {
    private int vehicleCategoryId;
    private String label;
    private String description;
}
