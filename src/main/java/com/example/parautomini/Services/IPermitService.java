package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Requests.PermitReq;
import com.example.parautomini.DTOs.Requests.PermitUpdateReq;
import com.example.parautomini.DTOs.Response.PermitDTO;

import java.util.List;

public interface IPermitService {
    PermitDTO add(PermitReq permitReq);
    PermitDTO get(int permitId);
    List<PermitDTO> getAll(int driverId);
    List<PermitDTO> getAllByPermitType(int permitTypeId);

    PermitDTO update(int permitId, PermitUpdateReq permitUpdateReq);

    void delete(int permitId);
    List<PermitDTO> getAllByDriverAndType(int driverId, int permitTypeId);
}
