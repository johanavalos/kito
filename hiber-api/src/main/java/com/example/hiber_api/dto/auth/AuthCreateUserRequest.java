package com.example.hiber_api.dto.auth;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthCreateUserRequest(@NotBlank String username,
                                    @NotBlank String password,
                                    @Size(min = 1, max=3) List<String> roles) {
}