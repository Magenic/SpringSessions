package com.robertw.EmployeeSkillsTracker.repository;

import com.robertw.EmployeeSkillsTracker.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
