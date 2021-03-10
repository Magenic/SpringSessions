package com.example.groupsession.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.groupsession.data.EmployeeRepository;
import com.example.groupsession.data.SkillRepository;
import com.example.groupsession.exceptions.RecordNotFoundException;
import com.example.groupsession.models.Employee;
import com.example.groupsession.models.Skill;
import com.example.groupsession.models.CreateSkillRequest;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository repository;
	@Autowired
	private SkillRepository skillRepository;
	
	public Page<Employee> get(Pageable pageRequest) {
		return repository.findAll(pageRequest);
	}
	
	public Employee get(Long id) throws RecordNotFoundException {
		Optional<Employee> employee = repository.findById(id);
		
		if (employee.isPresent()) {
			return employee.get();
		} else {
			throw new RecordNotFoundException("Employee not found.");
		}
	}
	
	public Employee create(Employee employee) {
		return repository.save(employee);
	}
	
	public Employee update(Long id, Employee employee) throws RecordNotFoundException {
		Employee existingEmployee = this.get(id);
		
		existingEmployee.setFirstname(employee.getFirstname());
		existingEmployee.setLastname(employee.getLastname());
		
		return repository.save(existingEmployee);
	}
	
	public void delete(Long id) throws RecordNotFoundException {
		this.get(id);
		repository.deleteById(id);
	}
	
	public Page<Skill> getSkills(Long id, Pageable pageRequest) throws RecordNotFoundException {
		return skillRepository.findAllByEmployeeId(id, pageRequest);
	}
	
	public Skill getSkill(Long id, Long skillId) throws RecordNotFoundException {
		this.get(id);
		
		Optional<Skill> skill = skillRepository.findById(skillId);
		if (!skill.isPresent()) {
			throw new RecordNotFoundException("Skill not found.");
		}
		if (skill.get().getEmployeeId() != id) {
			throw new RecordNotFoundException("Skill not found.");
		}
		
		return skill.get();
	}
	
	public Skill createSkill(Long employeeId, CreateSkillRequest request) throws RecordNotFoundException {
		Employee employee = this.get(employeeId);
		
		Skill skill = new Skill();
		skill.setDescription(request.getDescription());
		skill.setDuration(request.getDuration());
		skill.setLastUsed(request.getLastUsed());
		skill.setEmployeeId(employeeId);
		skill = skillRepository.save(skill);
		
		List<Skill> skills = employee.getSkills();
		if (skills == null) {
			skills = new ArrayList<Skill>();
		}
		skills.add(skill);
		employee.setSkills(skills);
		
		employee = repository.save(employee);
		return skill;
	}
	
	public Skill updateSkill(Long id, Long skillId, Skill skill) throws RecordNotFoundException {
		Skill existingSkill = this.getSkill(id, skillId);
		
		existingSkill.setDescription(skill.getDescription());
		existingSkill.setDuration(skill.getDuration());
		existingSkill.setLastUsed(skill.getLastUsed());
			
		return skillRepository.save(existingSkill);
	}
	
	public void deleteSkill(Long id, Long skillId) throws RecordNotFoundException {
		this.getSkill(id, skillId);
		skillRepository.deleteById(skillId);
	}
}
