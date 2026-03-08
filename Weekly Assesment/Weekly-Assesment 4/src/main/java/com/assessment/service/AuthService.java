package com.assessment.service;

import com.assessment.dto.AuthResponse;
import com.assessment.dto.LoginRequest;
import com.assessment.dto.RegistrationRequest;

public interface AuthService {
    AuthResponse register(RegistrationRequest request);
    AuthResponse login(LoginRequest request);
}
