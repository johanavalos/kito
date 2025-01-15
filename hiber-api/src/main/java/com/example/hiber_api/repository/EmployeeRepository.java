package com.example.hiber_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

import com.example.hiber_api.dto.EmployeeDTO;
import com.example.hiber_api.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    List<EmployeeDTO> findBy();

    Optional<EmployeeDTO> findEmployeeDTOById(Integer id);

    @Procedure(procedureName = "InsertEmployeesAndTasks")
    void createEmployees();
}