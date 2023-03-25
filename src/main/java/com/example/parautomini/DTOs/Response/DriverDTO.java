package com.example.parautomini.DTOs.Response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DriverDTO {
    private int driverId;
    private String registrationNumber;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String cin;
}
