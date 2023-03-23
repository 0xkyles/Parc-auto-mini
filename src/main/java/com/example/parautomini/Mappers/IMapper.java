package com.example.parautomini.Mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public interface IMapper<Entity, DTO> {
    Entity toEntity(DTO d);
    DTO toDTO(Entity e);
    DTO objToDTO (Object o);
    Entity objToEntity (Object o);
}
