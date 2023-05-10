package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Requests.HolidayReqDTO;
import com.example.parautomini.DTOs.Requests.HolidayUpdateReq;
import com.example.parautomini.DTOs.Response.HolidayDTO;

import java.util.List;

public interface IHolidayService {
    List<HolidayDTO> getAll();

    List<HolidayDTO> get(int driverId);

    void add(HolidayReqDTO holidayReqDTO);
    void delete(int holidayId);
    void deleteAll(int driverId);
    void update(int holidayId, HolidayUpdateReq holiday);
}
