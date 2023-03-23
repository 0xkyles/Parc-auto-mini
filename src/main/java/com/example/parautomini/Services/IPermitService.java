package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Requests.PermitReq;
import com.example.parautomini.DTOs.Response.PermitDTO;

import java.util.List;

public interface IPermitService {
    PermitDTO add(int driverId, PermitReq permitReq);
    PermitDTO get(int permitId);
    List<PermitDTO> getAll(int driverId);
}
