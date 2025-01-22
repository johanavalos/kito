package com.example.hiber_api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewEmployeeDTO {
    
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    @Pattern(regexp = "(fe)?male", message = "must be 'male' or 'female'")
    private String gender;

    @NotNull
    private Integer departmentId;

    @NotNull
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date dob;
}
