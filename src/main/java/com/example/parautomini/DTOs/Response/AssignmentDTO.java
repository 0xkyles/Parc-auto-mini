package com.example.parautomini.DTOs.Response;

import com.example.parautomini.Entites.Assignment;
import com.example.parautomini.Enums.VehicleTypeEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AssignmentDTO {
    private String driver;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date endDate;
    private String v_registrationNumber;
    private VehicleTypeEnum vehicleType;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date affectationDate;
    private Assignment.AssignmentStatus status;
}
