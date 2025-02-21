package com.example.kito.service;

import java.util.List;

import com.example.kito.dto.TaskDTO;
import com.example.kito.model.Task;

public interface ITaskService {

    List<TaskDTO> get();

    Task get(int id);

    void save(Task employee);

    void delete(int id);
}
