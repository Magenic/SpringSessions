package com.example.groupsession.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.groupsession.exceptions.RecordNotFoundException;
import com.example.groupsession.models.Employee;
import com.example.groupsession.models.Skill;
import com.example.groupsession.models.CreateSkillRequest;
import com.example.groupsession.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	@GetMapping()
	public ResponseEntity<Page<Employee>> get(@PageableDefault Pageable pageable) {
		return new ResponseEntity<Page<Employee>>(service.get(pageable), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> get(@PathVariable("id") Long id) throws RecordNotFoundException {
		return new ResponseEntity<Employee>(service.get(id), new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Employee> save(@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(service.create(employee), new HttpHeaders(), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> save(@PathVariable("id") Long id, @RequestBody Employee employee) throws RecordNotFoundException {
		return new ResponseEntity<Employee>(service.update(id, employee), new HttpHeaders(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(path = "/{id}/skills")
	public ResponseEntity<Page<Skill>> getBooks(@PageableDefault Pageable pageable, @PathVariable("id") Long id) throws RecordNotFoundException {
		return new ResponseEntity<Page<Skill>>(service.getSkills(id, pageable), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/skills/{skillId}")
	public ResponseEntity<Skill> get(@PathVariable("id") Long id, @PathVariable("skillId") Long skillId) throws RecordNotFoundException {
		return new ResponseEntity<Skill>(service.getSkill(id, skillId), new HttpHeaders(), HttpStatus.OK);
	}
	
	@PutMapping("/{id}/skills/{skillId}")
	public ResponseEntity<Skill> saveSkill(@PathVariable("id") Long id, @PathVariable("skillId") Long skillId, @RequestBody Skill skill) throws RecordNotFoundException {
		return new ResponseEntity<Skill>(service.updateSkill(id, skillId, skill), new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/{id}/skills")
	public ResponseEntity<Skill> saveSkill(@PathVariable("id") Long id, @RequestBody CreateSkillRequest skill) throws RecordNotFoundException {
		return new ResponseEntity<Skill>(service.createSkill(id, skill), new HttpHeaders(), HttpStatus.CREATED);
	
	}
	
	@DeleteMapping("/{id}/skills/{skillId}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id, @PathVariable("skillId") Long skillId) throws RecordNotFoundException {
		service.deleteSkill(id, skillId);
		return ResponseEntity.ok().build();
	}
}
