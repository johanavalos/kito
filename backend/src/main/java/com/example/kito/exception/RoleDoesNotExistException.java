package com.example.kito.exception;

public class RoleDoesNotExistException extends RuntimeException {
    public RoleDoesNotExistException(String role) {
        super("Role " + role + " does not exist.");
    }
}
