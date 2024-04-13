package com.example.MovieProject.service;

import com.example.MovieProject.dto.LoginCredentials;
import com.example.MovieProject.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDto registerUser(UserDto userdto);
    String loginUser(LoginCredentials loginCredentials);
    List<UserDto> getAll();
    UserDto findById(String id);
}
