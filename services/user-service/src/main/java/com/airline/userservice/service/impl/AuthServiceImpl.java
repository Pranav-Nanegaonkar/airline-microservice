package com.airline.userservice.service.impl;

import com.airline.commonlib.enums.UserRole;
import com.airline.commonlib.exception.ApiException;
import com.airline.commonlib.payload.request.auth.LoginRequest;
import com.airline.commonlib.payload.request.auth.RegisterRequest;
import com.airline.commonlib.payload.response.auth.AuthResponse;
import com.airline.userservice.entity.User;
import com.airline.userservice.mapper.UserMapper;
import com.airline.userservice.repository.UserRepository;
import com.airline.userservice.security.JwtService;
import com.airline.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw ApiException.badRequest("Email already registered");
        }

        User user = UserMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() == null ? UserRole.ROLE_USER : request.getRole());

        User save = userRepository.save(user);

        String token = jwtService.generateToken(save);

        return AuthResponse.builder()
                .token(token)
                .message("Registered Successfully")
                .title("USER-REGISTRATION")
                .user(UserMapper.toDTO(save))
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );
        User user = (User) auth.getPrincipal();
        String token = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .message("Logged In Successfully")
                .title("USER-LOGIN")
                .user(UserMapper.toDTO(user))
                .build();
    }
}
