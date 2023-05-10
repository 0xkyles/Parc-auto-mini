package com.example.parautomini.DTOs.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class HolidayDTO {
    private int holiday_id;
    private int driverId;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date endDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date addedDate;
}
