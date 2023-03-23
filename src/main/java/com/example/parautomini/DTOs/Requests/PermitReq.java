package com.example.parautomini.DTOs.Requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PermitReq {
    @NotBlank
    private int permitTypeId;
    @NotBlank(message = "Permit delivery date must not be blank")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date permitDeliveryDate;
}
