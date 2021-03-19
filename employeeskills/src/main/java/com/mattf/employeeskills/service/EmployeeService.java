package com.mattf.employeeskills.service;

import com.mattf.employeeskills.entity.Employee;
import com.mattf.employeeskills.exception.EmployeeNotFoundException;
import com.mattf.employeeskills.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository repository;

    public Employee createEmployee(String firstName, String lastName) {
       Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        return repository.saveAndFlush(employee);
    }

    public Page<Employee> getAllEmployees(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Employee updateEmployee(Employee employee) throws EmployeeNotFoundException {
        checkEmployeeExists(employee.getId());
        return repository.save(employee);
    }

    public void deleteEmployee(Long id) throws EmployeeNotFoundException {
        Employee employee = this.getByEmployeeId(id);
        repository.delete(employee);
    }

    private void checkEmployeeExists(Long employeeId) throws EmployeeNotFoundException {
        if (!repository.findById(employeeId).isPresent()) {
            throw new EmployeeNotFoundException();
        }
    }

    public Employee getByEmployeeId(Long id) throws EmployeeNotFoundException {
        Optional<Employee> employee = repository.findById(id);
        if (!employee.isPresent()) {
            throw new EmployeeNotFoundException();
        }
        return employee.get();
    }
}
