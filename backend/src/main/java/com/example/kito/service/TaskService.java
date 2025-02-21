package com.example.kito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kito.dto.TaskDTO;
import com.example.kito.model.Task;
import com.example.kito.repository.TaskRepository;

@Service
public class TaskService implements ITaskService{

    @Autowired
    private TaskRepository repository;

    @Transactional
    @Override
    public List<TaskDTO> get() {
        return repository.findBy();
    }

    @Transactional
    @Override
    public Task get(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Transactional
    @Override
    public void save(Task employee) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Transactional
    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
