package com.mattf.employeeskills.repository;

import com.mattf.employeeskills.exception.EmployeeNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<com.mattf.employeeskills.entity.Employee, Long> {
}
