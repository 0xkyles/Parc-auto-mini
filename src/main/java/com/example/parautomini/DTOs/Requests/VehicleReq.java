package com.example.parautomini.DTOs.Requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleReq {
    @NotBlank(message = "Registration number can't be blank")
    private String v_registrationNumber;
    @NotBlank(message = "Name can't be blank")
    private String name;
    @NotBlank(message = "Vehicle type has to be valid")
    private String vehicleType;
}
