package com.finalexercise.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.finalexercise.exception.RecordAlreadyExistException;
import com.finalexercise.exception.RecordNotFoundException;
import com.finalexercise.model.Employee;
import com.finalexercise.model.Skill;
import com.finalexercise.repository.EmployeeRepository;
import com.finalexercise.repository.SkillRepository;
import com.finalexercise.request.EmployeeRequest;
import com.finalexercise.request.SkillRequest;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private SkillRepository skillRepository;

	public Employee createEmployee(EmployeeRequest employeeRequest) throws RecordAlreadyExistException {
		List<Employee> existList = employeeRepository
				.findByFirstNameAndLastNameIgnoreCase(employeeRequest.getFirstName(), employeeRequest.getLastName());
		if (!existList.isEmpty()) {
			throw new RecordAlreadyExistException("Employee Already Exist");
		}

		Employee employee = new Employee();
		employee.setFirstName(employeeRequest.getFirstName());
		employee.setLastName(employeeRequest.getLastName());

		return employeeRepository.save(employee);
	}

	public Employee getEmployee(long employeeId) throws RecordNotFoundException {
		return employeeRepository.findById(employeeId)
				.orElseThrow(() -> new RecordNotFoundException("Employee does Not Exist"));

	}

	public Page<Employee> getAllEmployee(Pageable pageable) {
		return employeeRepository.findAll(pageable);
	}

	public Employee updateEmployee(long employeeId, EmployeeRequest employeeRequest) throws RecordNotFoundException {
		Employee employee = this.getEmployee(employeeId);

		employee.setFirstName(employeeRequest.getFirstName());
		employee.setLastName(employeeRequest.getLastName());
		return employeeRepository.save(employee);
	}

	public void deleteEmployee(long employeeId) throws RecordNotFoundException {
		Employee employee = this.getEmployee(employeeId);
		employeeRepository.delete(employee);
	}

	public Skill createEmployeeSkill(long employeeId, SkillRequest skillRequest) throws RecordNotFoundException {
		Employee employee = this.getEmployee(employeeId);

		Skill skill = new Skill();
		skill.setDescription(skillRequest.getDescription());
		skill.setDuration(skillRequest.getDuration());
		skill.setLastUsed(skillRequest.getLastUsed());
		skill.setEmployee(employee);

		return skillRepository.save(skill);
	}

	public Skill updateEmployeeSkill(long employeeId, long skillId, SkillRequest skillRequest)
			throws RecordNotFoundException {
		Employee employee = this.getEmployee(employeeId);
		Skill skill = this.skillRepository.findById(skillId)
				.orElseThrow(() -> new RecordNotFoundException("Skill does not exist for this user"));

		skill.setDescription(skillRequest.getDescription());
		skill.setDuration(skillRequest.getDuration());
		skill.setLastUsed(skillRequest.getLastUsed());
		skill.setEmployee(employee);

		return skillRepository.save(skill);
	}

	public Skill getEmployeeSkill(long employeeId, long skillId) throws RecordNotFoundException {
		this.getEmployee(employeeId);

		return this.skillRepository.findById(skillId)
				.orElseThrow(() -> new RecordNotFoundException("Skill does not exist for this user"));

	}

	public Page<Skill> getEmployeeAllSkill(long employeeId, Pageable pageable) throws RecordNotFoundException {
		this.getEmployee(employeeId);

		return Optional.ofNullable(this.skillRepository.findAllByEmployeeId(employeeId, pageable))
				.orElseThrow(() -> new RecordNotFoundException("Skill does not exist for this user"));

	}

	public void deleteEmployeeSkill(long employeeId, long skillId) throws RecordNotFoundException {
		Skill skill = this.getEmployeeSkill(employeeId, skillId);

		skillRepository.delete(skill);
	}

}