package com.example.parautomini.Controllers;

import com.example.parautomini.DTOs.Requests.UserCreateReq;
import com.example.parautomini.DTOs.Requests.UserUpdateReq;
import com.example.parautomini.DTOs.Response.Message;
import com.example.parautomini.DTOs.Response.UserDTO;
import com.example.parautomini.Services.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<Message> register(@RequestBody @Valid UserCreateReq user) {
        userService.register(user);
        return new ResponseEntity<>(new Message("User successfully created"), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> get(@PathVariable int userId) {
        return ResponseEntity.ok(userService.get(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Message> update(@PathVariable int userId, @RequestBody UserUpdateReq user) {
        userService.update(userId, user);
        return ResponseEntity.ok(new Message("User successfully updated"));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Message> delete(@PathVariable int userId) {
        userService.delete(userId);
        return ResponseEntity.ok(new Message("User deleted successfully"));
    }
}
