package com.example.hiber_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hiber_api.dto.EmployeeDTO;
import com.example.hiber_api.dto.NewEmployeeDTO;
import com.example.hiber_api.dto.UpdateEmployeeDTO;
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
    public EmployeeDTO getDto(int id) {
        Optional<EmployeeDTO> employee = repository.findEmployeeDTOById(id);
        if (employee == null) {
            throw new EmployeeNotFoundException(id);
        }
        return employee.orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Transactional
    @Override
    public Employee save(NewEmployeeDTO employee) {
        Employee toCreate = new Employee();
        toCreate.setName(employee.getName());
        toCreate.setGender(employee.getGender());
        toCreate.setDepartment(employee.getDepartment());
        toCreate.setDob(employee.getDob());
        Employee created = repository.save(toCreate);
        return created;
    }
    
    @Override
    public Employee update(Integer id, UpdateEmployeeDTO updateEmployee) {
        Employee toUpdate = repository.findById(id)
            .orElseThrow(() -> new EmployeeNotFoundException(id));
        
        if (updateEmployee.getName() != null) {toUpdate.setName(updateEmployee.getName());}
        if (updateEmployee.getDepartment() != null) {toUpdate.setDepartment(updateEmployee.getDepartment());}
        if (updateEmployee.getGender() != null) {toUpdate.setGender(updateEmployee.getGender());}
        if (updateEmployee.getDob() != null) {toUpdate.setDob(updateEmployee.getDob());}

        Employee updated = repository.save(toUpdate);

        return updated;
    }

    @Transactional
    @Override
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public Employee getEntity(int id) {
        return repository.findById(id)
            .orElseThrow(() -> new EmployeeNotFoundException(id));
    }
}
