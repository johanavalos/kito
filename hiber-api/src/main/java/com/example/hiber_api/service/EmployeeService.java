package com.example.hiber_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hiber_api.dto.EmployeeDTO;
import com.example.hiber_api.exception.EmployeeNotFoundException;
import com.example.hiber_api.model.Employee;
import com.example.hiber_api.repository.EmployeeRepository;

@Service
public class EmployeeService implements IEmployeeService{

    @Autowired
    private EmployeeRepository repository;

    @Transactional
    @Override
    public List<EmployeeDTO> get() {
        return repository.findBy();
    }

    @Transactional
    @Override
    public EmployeeDTO get(int id) {
        EmployeeDTO employee = repository.findByEmployeeId(id);
        if (employee == null) {
            throw new EmployeeNotFoundException(id);
        }
        return employee;
    }

    @Transactional
    @Override
    public void save(Employee employee) {
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
