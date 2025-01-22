package com.example.hiber_api.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.hiber_api.model.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer>{
    
}
