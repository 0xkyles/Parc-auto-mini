package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Requests.LicenseReq;
import com.example.parautomini.DTOs.Requests.LicenseUpdateReq;
import com.example.parautomini.DTOs.Response.LicenseDTO;

import java.util.List;

public interface ILicenseService {
    LicenseDTO add(LicenseReq licenseReq);
    LicenseDTO get(int permitId);
    List<LicenseDTO> getAll(int driverId);
    List<LicenseDTO> getAllByPermitType(int permitTypeId);

    LicenseDTO update(LicenseUpdateReq licenseUpdateReq);

    void delete(int permitId);
    List<LicenseDTO> getAllByDriverAndType(int driverId, int permitTypeId);
}
