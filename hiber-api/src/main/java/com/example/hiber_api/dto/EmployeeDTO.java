package com.example.hiber_api.dto;

import java.util.Date;

import com.example.hiber_api.model.Department;

public interface EmployeeDTO {

    public String getName();

    public String getGender();

    public Department getDepartment();

    public Date getDob();
}
