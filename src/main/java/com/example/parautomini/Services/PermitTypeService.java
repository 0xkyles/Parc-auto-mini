package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Requests.PermitTypeUpdateReq;
import com.example.parautomini.DTOs.Response.PermitTypeDTO;
import com.example.parautomini.Entites.PermitType;
import com.example.parautomini.Exceptions.ResourceNotFoundException;
import com.example.parautomini.Mappers.PermitTypeMapper;
import com.example.parautomini.Repositories.PermitTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermitTypeService implements IPermitTypeService {
    private final PermitTypeRepository permitTypeRepository;
    private final PermitTypeMapper permitTypeMapper;

    @Override
    public List<PermitTypeDTO> getAll() {
        return permitTypeRepository.findAll().stream().map(permitTypeMapper::toDTO).toList();
    }

    @Override
    public PermitTypeDTO get(int permitTypeId) {
        return permitTypeMapper.toDTO(this.findByPermitTypeId(permitTypeId));
    }

    @Override
    public PermitTypeDTO update(int permitTypeId, PermitTypeUpdateReq req) {
        return null;
    }

    private PermitType findByPermitTypeId(int permitTypeId) {
        return permitTypeRepository.findByPermitTypeId(permitTypeId).orElseThrow(
                () -> new ResourceNotFoundException("Permit type", "id", Integer.toString(permitTypeId))
        );
    }
}
