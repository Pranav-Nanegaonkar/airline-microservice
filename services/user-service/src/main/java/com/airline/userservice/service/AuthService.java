package com.airline.userservice.service;

import com.airline.commonlib.payload.request.auth.LoginRequest;
import com.airline.commonlib.payload.request.auth.RegisterRequest;
import com.airline.commonlib.payload.response.auth.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
