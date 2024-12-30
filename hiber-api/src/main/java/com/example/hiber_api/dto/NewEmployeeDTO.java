package com.example.hiber_api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class NewEmployeeDTO {
    
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    @Pattern(regexp = "(fe)?male", message = "must be 'male' or 'female'")
    private String gender;

    @NotNull
    @NotBlank
    private String department;

    @NotNull
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date dob;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
