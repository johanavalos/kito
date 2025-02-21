package com.example.kito.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.kito.model.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer>{
    
}
