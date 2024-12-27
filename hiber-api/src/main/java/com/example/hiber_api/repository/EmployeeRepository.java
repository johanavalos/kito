package com.example.hiber_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.hiber_api.dto.EmployeeDTO;
import com.example.hiber_api.model.Employee;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    List<EmployeeDTO> findBy();

    @Query(value = "select * from employee where id = :id", nativeQuery = true)
    EmployeeDTO findByEmployeeId(@Param("id") Integer id);
}