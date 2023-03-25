package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Requests.PermitTypeUpdateReq;
import com.example.parautomini.DTOs.Response.PermitTypeDTO;

import java.util.List;

public interface IPermitTypeService {
    List<PermitTypeDTO> getAll();
    PermitTypeDTO get(int permitTypeId);
    PermitTypeDTO update(int permitTypeId, PermitTypeUpdateReq req);
}
