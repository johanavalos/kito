package com.example.kito.controller.handler;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;

import com.example.kito.exception.DepartmentNotFoundException;
import com.example.kito.exception.PictureUploadFailException;
import com.example.kito.exception.ProfilePictureNotFoundException;
import com.example.kito.exception.RoleDoesNotExistException;
import com.example.kito.exception.UsernameAlreadyExistsException;

@RestControllerAdvice
class ExceptionAdvice {

    @ExceptionHandler(DepartmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String departmentNotFoundHandler(DepartmentNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> validationsHandler(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String NotReadableHandler(
        HttpMessageNotReadableException ex) {
        // String cause = ex.getMessage();
        
        // TODO Create specific error message
        String msg = "Invalid field format";
        return msg;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public String UsernameAlreadyExistsHandler(
        UsernameAlreadyExistsException ex) {
            return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RoleDoesNotExistException.class)
    public String RoleDoesNotExistHandler(
        RoleDoesNotExistException ex) {
            return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(PictureUploadFailException.class)
    public String PictureUploadFailHandler(
        PictureUploadFailException ex) {
            return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProfilePictureNotFoundException.class)
    public String ProfilePictureNotFoundHandler(
        ProfilePictureNotFoundException ex) {
            return ex.getMessage();
    }
}