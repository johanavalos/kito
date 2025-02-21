package com.example.hiber_api.service;

import java.util.List;

import com.example.hiber_api.dto.TaskDTO;
import com.example.hiber_api.model.Task;

public interface ITaskService {

    List<TaskDTO> get();

    Task get(int id);

    void save(Task employee);

    void delete(int id);
}
