package com.magenic.springboot.finals.service.impl;

import com.magenic.springboot.finals.exception.RecordNotFoundException;
import com.magenic.springboot.finals.models.Employee;
import com.magenic.springboot.finals.models.EmployeeInput;
import com.magenic.springboot.finals.repository.EmployeeRepository;
import com.magenic.springboot.finals.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    @Override
    public void deleteEmployee(long id) throws RecordNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            employeeRepository.delete(employee.get());
        }else{
            throw new RecordNotFoundException("Employee with id " + id + "is not existing");
        }
    }

    @Override
    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee getEmployeeById(long id) throws RecordNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }else{
            throw new RecordNotFoundException("Employee with id " + id + "is not existing");
        }
    }

    @Override
    public Employee createOrUpdateEmployee(EmployeeInput input) throws RecordNotFoundException {
        Employee employeeObject = null;
        if(input.getId() != null){
            Optional<Employee> employee = employeeRepository.findById(input.getId());
            if(employee.isPresent()){
                employeeObject = employee.get();
            }else{
                employeeObject = new Employee();
            }
        }else{
            employeeObject = new Employee();
        }
        employeeObject.setFirstName(input.getFirstName());
        employeeObject.setLastName(input.getLastName());
        return employeeRepository.save(employeeObject);

    }
}
