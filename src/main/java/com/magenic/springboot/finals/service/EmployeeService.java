package com.magenic.springboot.finals.service;

import com.magenic.springboot.finals.exception.RecordNotFoundException;
import com.magenic.springboot.finals.models.Employee;
import com.magenic.springboot.finals.models.EmployeeInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    public void deleteEmployee(long id) throws RecordNotFoundException;
    public Page<Employee> getAllEmployees(Pageable pageable);
    public Employee getEmployeeById(long id) throws RecordNotFoundException;
    public Employee createOrUpdateEmployee(EmployeeInput input) throws RecordNotFoundException;
}
