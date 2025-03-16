package com.example.kito.dto.auth;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username, message, accessToken", "status"})
public record AuthResponse(
    String username, 
    String message,
    String accessToken,
    boolean status
    ) {

}
