package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Requests.VehicleReq;
import com.example.parautomini.DTOs.Response.VehicleDTO;
import com.example.parautomini.Entites.Vehicle;
import com.example.parautomini.Entites.VehicleType;
import com.example.parautomini.Enums.VehicleTypeEnum;
import com.example.parautomini.Exceptions.APIException;
import com.example.parautomini.Exceptions.ResourceNotFoundException;
import com.example.parautomini.Mappers.VehicleMapper;
import com.example.parautomini.Repositories.VehicleRepository;
import com.example.parautomini.Repositories.VehicleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService implements IVehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleTypeRepository vehicleTypeRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    public VehicleDTO add(VehicleReq req) {
        var vehicleType = this.findVehileType(req.getVehicleType());
        String v_reg = req.getV_registrationNumber();
        boolean exists = vehicleRepository.existsById(v_reg);
        if(exists)
                throw new APIException("Vehicle with registration number " + v_reg + " already exists", HttpStatus.BAD_REQUEST);
        var vehicle = vehicleMapper.objToEntity(req);
        vehicle.setVehicleType(vehicleType);

        return vehicleMapper.toDTO(vehicleRepository.save(vehicle));
    }

    @Override
    public List<VehicleDTO> getAll() {
        return vehicleRepository.findAll().stream().map(vehicleMapper::toDTO).toList();
    }

    @Override
    public VehicleDTO getById(String v_registrationNumber) {
        return vehicleMapper.toDTO(this.findByVehicleId(v_registrationNumber));
    }

    @Override
    public List<VehicleDTO> getAllByVehicleType(String vehicleType) {
        return vehicleRepository.findAllByVehicleType(
                this.findVehileType(vehicleType)
                )
                .stream()
                .map(vehicleMapper::toDTO)
                .toList();
    }

    @Override
    public VehicleDTO update(String v_registrationNumber, VehicleReq req) {
        return null;
    }

    @Override
    public void delete(String v_registrationNumber) {
        vehicleRepository.delete(this.findByVehicleId(v_registrationNumber));
    }

    private VehicleType findVehileType(String vehicleTypeId) {
        return vehicleTypeRepository.findByLabel(vehicleTypeId).orElseThrow(
                () -> new ResourceNotFoundException("Vehicle Type", "id", vehicleTypeId)
        );
    }

    private Vehicle findByVehicleId(String v_registrationNumber) {
        return vehicleRepository.findById(v_registrationNumber).orElseThrow(
                () -> new ResourceNotFoundException("Vehicle", "id", v_registrationNumber)
        );
    }
}
