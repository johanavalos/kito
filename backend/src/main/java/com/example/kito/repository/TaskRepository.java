package com.example.kito.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.kito.dto.TaskDTO;
import com.example.kito.model.Task;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TaskRepository extends CrudRepository<Task, Integer> {

    List<TaskDTO> findBy();
}