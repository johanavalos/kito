package com.example.hiber_api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateEmployeeDTO {
    
    private String name;

    @Pattern(regexp = "(fe)?male", message = "must be 'male' or 'female'")
    private String gender;

    private Integer departmentId;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date dob;
}
