package com.assessment.service.impl;

import com.assessment.dto.AuthResponse;
import com.assessment.dto.LoginRequest;
import com.assessment.dto.RegistrationRequest;
import com.assessment.entity.User;
import com.assessment.exception.InvalidRequestException;
import com.assessment.repository.UserRepository;
import com.assessment.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    @Override
    public AuthResponse register(RegistrationRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new InvalidRequestException("Email already registered");
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());

        User saved = userRepository.save(user);
        return new AuthResponse(saved.getId(), saved.getFullName(), saved.getEmail(), saved.getRole(), "Registration successful");
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidRequestException("Invalid email or password"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new InvalidRequestException("Invalid email or password");
        }

        return new AuthResponse(user.getId(), user.getFullName(), user.getEmail(), user.getRole(), "Login successful");
    }
}
