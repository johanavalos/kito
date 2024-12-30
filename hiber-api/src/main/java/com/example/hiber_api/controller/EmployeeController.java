package com.example.hiber_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hiber_api.dto.EmployeeDTO;
import com.example.hiber_api.dto.NewEmployeeDTO;
import com.example.hiber_api.model.Employee;
import com.example.hiber_api.service.IEmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeController extends Controller{

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping
    public List<EmployeeDTO> get() {
        return employeeService.get();
    }

    @GetMapping("/{id}")
    EmployeeDTO getById(@PathVariable Integer id) {
        return employeeService.get(id);
    }

    @PostMapping
    public ResponseEntity<Employee> create(@Valid @RequestBody NewEmployeeDTO employee) {
        Employee created = employeeService.save(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // @PatchMapping("/{id}")
    // public Employee patch(@PathVariable Integer id, @RequestBody Employee employee){
    //     Employee oldEmployee = employeeService.findById(id)
    //         .orElseThrow(() -> new EmployeeNotFoundException(id));

    //     try {
    //         employee = (Employee) convertUsingReflection(employee, oldEmployee);
    //     } catch (IllegalAccessException e) {
    //         System.err.println(e);
    //     }

    //     Employee updated = employeeService.save(employee);
    //     return updated;
    // }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        employeeService.deleteById(id);
    }

}