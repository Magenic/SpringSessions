package com.robertw.EmployeeSkillsTracker.service;

import com.robertw.EmployeeSkillsTracker.exception.EmployeeNotFoundException;
import com.robertw.EmployeeSkillsTracker.model.Employee;
import com.robertw.EmployeeSkillsTracker.repository.EmployeeRepository;
import com.robertw.EmployeeSkillsTracker.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Page<Employee> getAllEmployees(Pageable page) {
        return this.employeeRepository.findAll(page);
    }

    @Override
    public Employee getEmployeeById(long employeeId) throws EmployeeNotFoundException {
        return this.employeeRepository.findById(employeeId)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee createOrUpdateEmployee(Employee employee) {
        Employee employeeToCreateOrUpdate;
        Optional<Employee> employeeFound = this.employeeRepository.findById(employee.getId());

        if (employeeFound.isPresent()) {
            employeeToCreateOrUpdate = employeeFound.get();
            employeeToCreateOrUpdate.setFirstName(employee.getFirstName());
            employeeToCreateOrUpdate.setLastName(employee.getLastName());
            employeeToCreateOrUpdate.setRole(employee.getRole());
        }
        else {
            employeeToCreateOrUpdate = employee;
            employeeToCreateOrUpdate.setSkills(Collections.emptyList());
        }

        Employee newEmployee = this.employeeRepository.save(employeeToCreateOrUpdate);
        return newEmployee;
    }

    @Override
    public void deleteEmployeeById(long employeeId) throws EmployeeNotFoundException {
        this.getEmployeeById(employeeId);
        this.employeeRepository.deleteById(employeeId);
    }
}
