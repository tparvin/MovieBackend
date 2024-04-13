package com.example.MovieProject.controller;

import com.example.MovieProject.dto.LoginCredentials;
import com.example.MovieProject.dto.UserDto;
import com.example.MovieProject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private  final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register-user")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUserDto = userService.registerUser(userDto);
        return ResponseEntity.ok(createdUserDto);
    }

    @PostMapping("/login-user")
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginCredentials loginCredentials) {
        return ResponseEntity.ok(userService.loginUser(loginCredentials));
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtoList = userService.getAll();
        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        UserDto userDto = userService.findById(id);
        return ResponseEntity.ok(userDto);
    }
}
