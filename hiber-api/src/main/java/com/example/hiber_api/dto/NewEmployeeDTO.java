package com.example.hiber_api.dto;

import java.util.Date;

import jakarta.validation.constraints.NotNull;

public class NewEmployeeDTO {
    
    @NotNull
    private String name;

    @NotNull
    private String gender;

    @NotNull
    private String department;

    @NotNull
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
