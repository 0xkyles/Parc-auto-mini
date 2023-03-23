package com.example.parautomini.Services;

import com.example.parautomini.DTOs.Requests.UserCreateReq;
import com.example.parautomini.DTOs.Requests.UserUpdateReq;
import com.example.parautomini.DTOs.Response.UserDTO;
import com.example.parautomini.Entites.User;
import com.example.parautomini.Exceptions.APIException;
import com.example.parautomini.Exceptions.ResourceNotFoundException;
import com.example.parautomini.Mappers.UserMapper;
import com.example.parautomini.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public UserDTO register(UserCreateReq newUser) {
        if(!newUser.getPassword().equals(newUser.getConfirmPassword())) {
            throw new APIException("Password have to match", HttpStatus.BAD_REQUEST);
        }

        var user = userMapper.objToEntity(newUser);
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserDTO get(int userId) {
        var user = this.findUser(userId);

        return userMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> getAll() {
        return userRepository.findAll().stream().map(userMapper::toDTO).toList();
    }

    @Override
    public UserDTO update(int userId, UserUpdateReq updatedUser) {
        var user = this.findUser(userId);
        String email = updatedUser.getEmail();
        String password = updatedUser.getPassword();
        if(email != null)
            user.setEmail(email);
        if(password != null)
            user.setPassword(password);

        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public void delete(int userId) {
        var user = this.findUser(userId);
        userRepository.delete(user);
    }

    private User findUser(int userId) {
        return userRepository.findByUserId(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", Integer.toString(userId))
        );
    }
}
