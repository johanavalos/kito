package com.example.hiber_api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.hiber_api.dto.TaskDTO;
import com.example.hiber_api.model.Task;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TaskRepository extends CrudRepository<Task, Integer> {

    List<TaskDTO> findBy();
}