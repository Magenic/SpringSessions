package com.example.finalexercise.services;

import com.example.finalexercise.data.EmployeeRepository;
import com.example.finalexercise.exceptions.EmployeeNotFoundException;
import com.example.finalexercise.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Page<Employee> get(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Employee get(Long id) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new EmployeeNotFoundException("Employee not found.");
        }
    }

    public Employee createOrUpdate(Employee request) throws EmployeeNotFoundException {
        if (request.getId() == null) {
            return this.create(request);
        } else {
            return this.update(request);
        }
    }

    private Employee update(Employee request) throws EmployeeNotFoundException {
        Employee employee = this.get(request.getId());
        employee.setFirstname(request.getFirstname());
        employee.setLastname(request.getLastname());
        return employeeRepository.save(employee);
    }

    public Employee create(Employee request) {
        return employeeRepository.save(request);
    }

    public void delete(Long id) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            employeeRepository.deleteById(id);
        } else {
            throw new EmployeeNotFoundException("Employee not found.");
        }
    }
}
