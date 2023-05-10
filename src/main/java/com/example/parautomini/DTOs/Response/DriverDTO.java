package com.example.parautomini.DTOs.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DriverDTO {
    private int driverId;
    private UserDTO user;
    private String registrationNumber;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthday;
    private String cin;
}
