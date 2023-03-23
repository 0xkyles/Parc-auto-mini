package com.example.parautomini.DTOs.Requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class DriverReq {
    @NotBlank(message = "registration number should not be empty")
    private String registrationNumber;
    @NotBlank(message = "firstName should not be empty")
    private String firstName;
    @NotBlank(message = "lastName should not be empty")
    private String lastName;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date birthday;
    @NotBlank(message = "CIN should not be empty")
    private String cin;
    @Positive(message = "User id should be valid")
    private int userId;
}
