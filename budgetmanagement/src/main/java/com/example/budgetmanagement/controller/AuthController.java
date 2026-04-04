package com.example.budgetmanagement.controller;

import com.example.budgetmanagement.dto.AuthRequest;
import com.example.budgetmanagement.dto.AuthResponse;
import com.example.budgetmanagement.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        // 1. Authenticate username & password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()));

        // 2. If valid, generate JWT token
        String token = jwtUtil.generateToken(request.getUsername());

        // 3. Return token to client
        return new AuthResponse(token);
    }
}