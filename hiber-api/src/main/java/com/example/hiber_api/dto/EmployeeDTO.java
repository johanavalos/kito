package com.example.hiber_api.dto;

import java.util.Date;
import java.util.List;

public interface EmployeeDTO {

    String getName();

    String getGender();

    String getDepartment();

    Date getDob();

    List<TaskDTO> getTasks();
}
