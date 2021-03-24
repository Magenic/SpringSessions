package com.robertw.EmployeeSkillsTracker.controller;

import com.robertw.EmployeeSkillsTracker.exception.EmployeeNotFoundException;
import com.robertw.EmployeeSkillsTracker.model.Employee;
import com.robertw.EmployeeSkillsTracker.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<Page<Employee>> getAllEmployees(Pageable page) {
        Page<Employee> employees = this.employeeService.getAllEmployees(page);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("{employeeId}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long employeeId) throws EmployeeNotFoundException {
        Employee employeeFound = this.employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeFound);
    }

    @PostMapping
    public ResponseEntity<Employee> createOrUpdateEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = this.employeeService.createOrUpdateEmployee(employee);
        return ResponseEntity.ok(createdEmployee);
    }

    @DeleteMapping("{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long employeeId) throws EmployeeNotFoundException {
        this.employeeService.deleteEmployeeById(employeeId);
        return ResponseEntity.ok("Employee deleted");
    }
}
