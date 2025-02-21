package com.example.kito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kito.dto.TaskDTO;
import com.example.kito.service.ITaskService;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/task")
public class TaskController extends Controller{

    @Autowired
    private ITaskService taskService;

    @GetMapping
    public List<TaskDTO> get() {
        return taskService.get();
    }
    


}
