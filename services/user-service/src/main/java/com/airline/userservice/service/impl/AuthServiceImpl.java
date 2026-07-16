package com.airline.userservice.service.impl;

import com.airline.commonlib.exception.ApiException;
import com.airline.commonlib.payload.request.auth.LoginRequest;
import com.airline.commonlib.payload.request.auth.RegisterRequest;
import com.airline.commonlib.payload.response.auth.AuthResponse;
import com.airline.userservice.entity.User;
import com.airline.userservice.mapper.UserMapper;
import com.airline.userservice.repository.UserRepository;
import com.airline.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw ApiException.badRequest("Email already exists");
        }

        User user = UserMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setLastLogin(LocalDateTime.now());

        User save = userRepository.save(user);
        return AuthResponse.builder()
                .token("TOKEN")
                .message("Registered Successfully")
                .title("USER-REGISTRATION")
                .user(UserMapper.toDTO(save))
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        if (!userRepository.existsByEmail(request.getEmail())) {
            throw ApiException.badRequest("Email does not exists, Register first");
        }
        User user = userRepository.findByEmail(request.getEmail());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw ApiException.badRequest("Invalid Credentials");
        }

        user.setLastLogin(LocalDateTime.now());
        User save = userRepository.save(user);

        return AuthResponse.builder()
                .token("TOKEN")
                .message("Logged In Successfully")
                .title("USER-LOGIN")
                .user(UserMapper.toDTO(save))
                .build();
    }
}
