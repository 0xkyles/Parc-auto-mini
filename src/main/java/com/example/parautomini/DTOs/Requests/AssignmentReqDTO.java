package com.example.parautomini.DTOs.Requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignmentReqDTO {
    @NotEmpty(message = "Trip must be given")
    private int tripId;
    @NotEmpty(message = "Driver must be given")
    private int driverId;
    @NotEmpty(message = "Vehicle must be given")
    private String v_registrationNumber;
}
