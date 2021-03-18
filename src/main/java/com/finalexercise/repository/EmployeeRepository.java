package com.finalexercise.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.finalexercise.model.Employee;


@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
	Page<Employee> findAll(Pageable pageable);

	List<Employee> findByFirstNameAndLastNameIgnoreCase(String firstName, String lastName);
}
