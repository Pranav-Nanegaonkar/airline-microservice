package com.airline.userservice.controller;

import com.airline.commonlib.payload.request.auth.LoginRequest;
import com.airline.commonlib.payload.request.auth.RegisterRequest;
import com.airline.commonlib.payload.response.auth.AuthResponse;
import com.airline.userservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse register = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(register);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse login = authService.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(login);
    }
}
