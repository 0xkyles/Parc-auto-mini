package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Requests.LicenseTypeUpdateReq;
import com.example.parautomini.DTOs.Response.LicenseTypeDTO;

import java.util.List;

public interface ILicenseTypeService {
    List<LicenseTypeDTO> getAll();
    LicenseTypeDTO get(int licenseTypeId);
    LicenseTypeDTO update(int licenseTypeId, LicenseTypeUpdateReq req);
}
