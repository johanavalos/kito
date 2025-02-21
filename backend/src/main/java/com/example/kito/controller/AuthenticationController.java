package com.example.kito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kito.dto.auth.AuthCreateUserRequest;
import com.example.kito.dto.auth.AuthLoginRequest;
import com.example.kito.dto.auth.AuthResponse;
import com.example.kito.service.AuthenticationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService userDetailsService;

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
