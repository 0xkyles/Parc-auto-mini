package com.example.parautomini.DTOs.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PermitDTO {
    private int permitId;
    private Date permitDeliveryDate;
    private String permitType;
    private int driverId;
}
