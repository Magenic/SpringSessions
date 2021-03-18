package com.finalexercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalexercise.exception.RecordAlreadyExistException;
import com.finalexercise.exception.RecordNotFoundException;
import com.finalexercise.model.Employee;
import com.finalexercise.model.Skill;
import com.finalexercise.request.EmployeeRequest;
import com.finalexercise.request.SkillRequest;
import com.finalexercise.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest) throws RecordAlreadyExistException{
		return employeeService.createEmployee(employeeRequest);
	}
	

	@GetMapping("{employeeId}")
	public Employee getEmployeeById(@PathVariable("employeeId") Integer employeeId) throws RecordNotFoundException{
		return employeeService.getEmployee(employeeId);
	}
	
	@GetMapping
	public Page<Employee> getAllEmployees(Pageable pageable){
		return employeeService.getAllEmployee(pageable);
	}
	

	@PutMapping("{employeeId}")
	public Employee updateEmployee(@PathVariable("employeeId") Integer employeeId, @RequestBody EmployeeRequest employeeRequest) throws RecordNotFoundException{
		return employeeService.updateEmployee(employeeId, employeeRequest);
	}
	
	@DeleteMapping("{employeeId}")
	public void deleteEmployee(@PathVariable("employeeId") Integer employeeId) throws RecordNotFoundException{
		 employeeService.deleteEmployee(employeeId);
	}
	
	@PostMapping("{employeeId}/skills")
	public Skill addEmployeeSkill(@PathVariable("employeeId") Integer employeeId, @RequestBody SkillRequest skillRequest) throws RecordNotFoundException{
		return employeeService.createEmployeeSkill(employeeId, skillRequest);
	}
	
	@PutMapping("{employeeId}/skills/{skillId}")
	public Skill updateEmployeeSkill(@PathVariable("employeeId") Integer employeeId ,@PathVariable("skillId") Integer skillId , @RequestBody SkillRequest skillRequest) throws RecordNotFoundException{
		return employeeService.updateEmployeeSkill(employeeId, skillId, skillRequest);
	}
	
	@GetMapping("{employeeId}/skills")
	public Page<Skill> getEmployeSkills(@PathVariable("employeeId") Integer employeeId, Pageable pageable) throws RecordNotFoundException{
		return employeeService.getEmployeeAllSkill(employeeId, pageable);
	}

	@DeleteMapping("{employeeId}/skills/{skillId}")
	public void deleteEmployeeSkill(@PathVariable("employeeId") Integer employeeId ,@PathVariable("skillId") Integer skillId ) throws RecordNotFoundException{
		 employeeService.deleteEmployeeSkill(employeeId,skillId);
	}
	
	
	
	
	
}
