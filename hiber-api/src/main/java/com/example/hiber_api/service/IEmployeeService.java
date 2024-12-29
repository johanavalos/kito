package com.example.hiber_api.service;

import java.util.List;

import com.example.hiber_api.dto.EmployeeDTO;
import com.example.hiber_api.dto.NewEmployeeDTO;
import com.example.hiber_api.model.Employee;

public interface IEmployeeService {

    List<EmployeeDTO> get();

    EmployeeDTO get(int id);

    Employee save(NewEmployeeDTO employee);

    void delete(int id);
}
