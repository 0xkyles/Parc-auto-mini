package com.example.parautomini.Mappers;

import com.example.parautomini.DTOs.Response.UserDTO;
import com.example.parautomini.Entites.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper implements IMapper<User, UserDTO>{
    private final ModelMapper modelMapper;

    @Override
    public User toEntity(UserDTO d) {
        return modelMapper.map(d, User.class);
    }

    @Override
    public UserDTO toDTO(User e) {
        return modelMapper.map(e, UserDTO.class);
    }

    @Override
    public UserDTO objToDTO(Object o) {
        return modelMapper.map(o, UserDTO.class);
    }

    @Override
    public User objToEntity(Object o) {
        return modelMapper.map(o, User.class);
    }
}
