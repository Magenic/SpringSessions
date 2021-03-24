package com.robertw.EmployeeSkillsTracker.service.interfaces;

import com.robertw.EmployeeSkillsTracker.exception.EmployeeNotFoundException;
import com.robertw.EmployeeSkillsTracker.exception.SkillNotFoundException;
import com.robertw.EmployeeSkillsTracker.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    Page<Employee> getAllEmployees(Pageable page);
    Employee getEmployeeById(long employeeId) throws EmployeeNotFoundException;
    Employee createOrUpdateEmployee(Employee employee);
    void deleteEmployeeById(long employeeId) throws EmployeeNotFoundException;
}
