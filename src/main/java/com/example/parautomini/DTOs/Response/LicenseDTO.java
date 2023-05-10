package com.example.parautomini.DTOs.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LicenseDTO {
    private int licenseId;
    private int driverId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date deliveryDate;
    private String licenseType;
}
