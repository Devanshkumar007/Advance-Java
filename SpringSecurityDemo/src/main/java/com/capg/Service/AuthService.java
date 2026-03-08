package com.capg.Service;

import com.capg.Entity.User;
import com.capg.Enums.Role;
import com.capg.Repository.UserRepository;
import com.capg.Security.JwtUtil;
import com.capg.dto.AuthResponse;
import com.capg.dto.LoginRequest;
import com.capg.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;

    public String register(RegisterRequest request){
        User user = modelMapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        try {
            user.setRole(Role.valueOf(request.getRole().trim().toUpperCase()));
        } catch (IllegalArgumentException | NullPointerException ex) {
            throw new RuntimeException("Invalid role. Allowed values: USER, ADMIN");
        }

        userRepo.save(user);

        return "USER CREATED" ;
    }


    public AuthResponse login(LoginRequest request){
        User user = userRepo.findByUsername(request.getUsername()).orElseThrow(()-> new RuntimeException("USER NOT FOUND"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Credentials");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole().name());

        return new AuthResponse(token, user.getUsername(), user.getRole().name());
    }
}


