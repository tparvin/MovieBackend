package com.example.MovieProject.service.impl;

import com.example.MovieProject.dto.LoginCredentials;
import com.example.MovieProject.dto.UserDto;
import com.example.MovieProject.exception.InvalidCredentialsException;
import com.example.MovieProject.exception.RecordNotFoundException;
import com.example.MovieProject.model.User;
import com.example.MovieProject.repository.UserRepository;
import com.example.MovieProject.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = toEntity(userDto);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return toDto(user);
    }

    @Override
    public String loginUser(LoginCredentials loginCredentials) {
        User user = userRepository.findByEmail(loginCredentials.getEmail())
                .orElseThrow(() -> new RecordNotFoundException("User not found with email: " + loginCredentials.getEmail()));

        if (!passwordEncoder.matches(loginCredentials.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Password is incorrect");
        }

        return "User logged in successfully";
    }

    @Override
    public List<UserDto> getAll() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : userList) {
            UserDto userDto = toDto(user);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Override
    public UserDto findById(String id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return toDto(user);
        } else {
            throw new RecordNotFoundException(String.format("User not found for id => %s", id));
        }
    }


    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public User toEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
}
