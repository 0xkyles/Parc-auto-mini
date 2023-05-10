package com.example.parautomini.DTOs.Requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LicenseReq {
    @Positive(message = "License type id should be valid")
    private int licenseTypeId;
    @Positive(message = "Driver id should be valid")
    private int driverId;
    @NotNull(message = "License delivery date must be provided")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date deliveryDate;
}
