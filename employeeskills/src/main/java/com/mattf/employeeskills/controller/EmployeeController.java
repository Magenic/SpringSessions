package com.mattf.employeeskills.controller;

import com.mattf.employeeskills.entity.Employee;
import com.mattf.employeeskills.exception.EmployeeNotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    @Autowired
    com.mattf.employeeskills.service.EmployeeService service;


    @PostMapping
    public ResponseEntity<com.mattf.employeeskills.entity.Employee> createOrUpdateEmployee(@RequestBody Employee employee) throws EmployeeNotFoundException {
        if (employee.getId() == null) {
            Employee newAcc = service.createEmployee(employee.getFirstName(), employee.getLastName());
            return new ResponseEntity<>(newAcc, new HttpHeaders(), HttpStatus.CREATED);
        }
        Employee updatedEmployee = service.updateEmployee(employee);
        return new ResponseEntity<>(updatedEmployee, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) throws EmployeeNotFoundException {
        Employee employee = service.getByEmployeeId(id);
        return new ResponseEntity<>(employee, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Employee>> getAllEmployees(Pageable pageable) {
        Page<Employee> employees = service.getAllEmployees(pageable);
        return new ResponseEntity<>(employees, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) throws EmployeeNotFoundException {
        service.deleteEmployee(id);
        return new ResponseEntity<String>("Employee was deleted", new HttpHeaders(), HttpStatus.OK);
    }
}
