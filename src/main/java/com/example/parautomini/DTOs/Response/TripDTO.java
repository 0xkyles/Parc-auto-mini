package com.example.parautomini.DTOs.Response;

import com.example.parautomini.Enums.VehicleTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TripDTO {
    private int tripId;
    private Date startDate;
    private Date endDate;
    private boolean isAssigned;
    private VehicleTypeEnum vehicleType;
}
