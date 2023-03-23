package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Requests.PermitReq;
import com.example.parautomini.DTOs.Response.PermitDTO;
import com.example.parautomini.Entites.Driver;
import com.example.parautomini.Entites.Permit;
import com.example.parautomini.Entites.PermitType;
import com.example.parautomini.Enums.PermitTypeEnum;
import com.example.parautomini.Exceptions.ResourceNotFoundException;
import com.example.parautomini.Mappers.PermitMapper;
import com.example.parautomini.Repositories.DriverRepository;
import com.example.parautomini.Repositories.PermitRepository;
import com.example.parautomini.Repositories.PermitTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermitService implements IPermitService {
    private final PermitRepository permitRepository;
    private final PermitTypeRepository permitTypeRepository;
    private final DriverRepository driverRepository;
    private final PermitMapper permitMapper;

    public PermitDTO add(int driverId, PermitReq permitReq) {
        int permitTypeId = permitReq.getPermitTypeId();
        var driver = this.findDriver(driverId);
        var permitType = this.findPermitType(permitTypeId);
        var permit = permitMapper.objToEntity(permitReq);
        permit.setPermitType(permitType);
        permit.setDriver(driver);

        return permitMapper.toDTO(permitRepository.save(permit));
    }

    public PermitDTO get(int permitId) {
        var permit = this.findPermit(permitId);
        return permitMapper.toDTO(permit);
    }

    public List<PermitDTO> getAll(int driverId) {
        return permitRepository.findAllByDriverId(driverId).stream().map(permitMapper::toDTO).toList();
    }

    private Driver findDriver(int driverId) {
        return driverRepository.findByDriverId(driverId).orElseThrow(
                () -> new ResourceNotFoundException("Driver", "id", Integer.toString(driverId))
        );
    }

    private Permit findPermit(int permitId) {
        return permitRepository.findByPermitId(permitId).orElseThrow(
                () -> new ResourceNotFoundException("Permit", "id", Integer.toString(permitId))
        );
    }

    private PermitType findPermitType(int permitTypeId) {
        return permitTypeRepository.findByPermitTypeId(permitTypeId).orElseThrow(
                () -> new ResourceNotFoundException("Permit type", "id", Integer.toString(permitTypeId))
        );
    }
}
