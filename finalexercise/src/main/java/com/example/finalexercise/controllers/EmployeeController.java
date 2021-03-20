package com.example.finalexercise.controllers;

import com.example.finalexercise.exceptions.EmployeeNotFoundException;
import com.example.finalexercise.exceptions.SkillNotFoundException;
import com.example.finalexercise.models.Employee;
import com.example.finalexercise.models.Skill;
import com.example.finalexercise.services.EmployeeService;
import com.example.finalexercise.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SkillService skillService;

    @GetMapping()
    public ResponseEntity<Page<Employee>> get(@PageableDefault Pageable pageable) {
        return new ResponseEntity<>(employeeService.get(pageable), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        return new ResponseEntity<>(employeeService.get(id), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> save(@RequestBody Employee request) throws EmployeeNotFoundException {
        return new ResponseEntity<>(employeeService.createOrUpdate(request), new HttpHeaders(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/skills")
    public ResponseEntity<Skill> createSkill(@PathVariable("id") Long id, @RequestBody Skill request) throws EmployeeNotFoundException {
        return new ResponseEntity<>(skillService.create(request, id), new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/{id}/skills/{skillId}")
    public ResponseEntity<Skill> updateSkill(@PathVariable("id") Long id, @RequestBody Skill request, @PathVariable("skillId") Long skillId) throws SkillNotFoundException, EmployeeNotFoundException {
        return new ResponseEntity<>(skillService.update(request, id, skillId), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}/skills")
    public ResponseEntity<Page<Skill>> getSkills(@PageableDefault Pageable pageable, @PathVariable Long id) throws EmployeeNotFoundException {
        return new ResponseEntity<>(skillService.get(pageable, id), new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/skills/{skillId}")
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id, @PathVariable Long skillId) throws EmployeeNotFoundException, SkillNotFoundException {
        skillService.delete(id, skillId);
        return ResponseEntity.ok().build();
    }
}
