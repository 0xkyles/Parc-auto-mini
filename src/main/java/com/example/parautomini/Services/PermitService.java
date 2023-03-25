package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Requests.PermitReq;
import com.example.parautomini.DTOs.Requests.PermitUpdateReq;
import com.example.parautomini.DTOs.Response.PermitDTO;
import com.example.parautomini.Entites.Driver;
import com.example.parautomini.Entites.Permit;
import com.example.parautomini.Entites.PermitType;
import com.example.parautomini.Exceptions.ResourceNotFoundException;
import com.example.parautomini.Mappers.PermitMapper;
import com.example.parautomini.Repositories.DriverRepository;
import com.example.parautomini.Repositories.PermitRepository;
import com.example.parautomini.Repositories.PermitTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PermitService implements IPermitService {
    private final PermitRepository permitRepository;
    private final PermitTypeRepository permitTypeRepository;
    private final DriverRepository driverRepository;
    private final PermitMapper permitMapper;

    public PermitDTO add(PermitReq permitReq) {
        int permitTypeId = permitReq.getPermitTypeId();
        var driver = this.findDriver(permitReq.getDriverId());
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

    @Override
    public List<PermitDTO> getAllByPermitType(int permitTypeId) {
        return permitRepository.findAllByPermitTypeId(permitTypeId).stream().map(permitMapper::toDTO).toList();
    }

    @Override
    public PermitDTO update(int permitId, PermitUpdateReq permitUpdateReq) {
        var permit = this.findPermit(permitId);
        int permitTypeId = permitUpdateReq.getPermitTypeId();
        Date delivery = permitUpdateReq.getPermitDeliveryDate();

        if(permitTypeId > 0) {
            var permitType = this.findPermitType(permitTypeId);
            permit.setPermitType(permitType);
        }
        if(delivery != null)
        {
            permit.setPermitDeliveryDate(delivery);
        }

        return permitMapper.toDTO(permitRepository.save(permit));
    }

    @Override
    public void delete(int permitId) {
        var permit = this.findPermit(permitId);
        permitRepository.delete(permit);
    }

    @Override
    public List<PermitDTO> getAllByDriverAndType(int driverId, int permitTypeId) {
        return permitRepository.findAllByDriverAndType(driverId, permitTypeId).stream().map(permitMapper::toDTO).toList();
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
