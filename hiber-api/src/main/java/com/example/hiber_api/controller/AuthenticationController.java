package com.example.hiber_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hiber_api.dto.auth.AuthCreateUserRequest;
import com.example.hiber_api.dto.auth.AuthLoginRequest;
import com.example.hiber_api.dto.auth.AuthResponse;
import com.example.hiber_api.service.UserDetailsServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(
            @RequestBody @Valid AuthCreateUserRequest userRequest){
        return new ResponseEntity<>(userDetailsService.createUser(userRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody @Valid AuthLoginRequest request) {
        return new ResponseEntity<>(userDetailsService.loginUser(request), HttpStatus.OK);
    }
    
}
