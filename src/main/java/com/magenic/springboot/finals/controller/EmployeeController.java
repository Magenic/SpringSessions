package com.magenic.springboot.finals.controller;

import com.magenic.springboot.finals.exception.RecordNotFoundException;
import com.magenic.springboot.finals.models.Employee;
import com.magenic.springboot.finals.models.EmployeeInput;
import com.magenic.springboot.finals.models.Skills;
import com.magenic.springboot.finals.models.SkillsInput;
import com.magenic.springboot.finals.service.EmployeeService;
import com.magenic.springboot.finals.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    SkillsService skillsService;

    @PostMapping()
    public ResponseEntity<Employee> createEmployee(
            @RequestBody EmployeeInput input
    ) throws RecordNotFoundException {

        Employee employee1 = employeeService.createOrUpdateEmployee(input);

        return new ResponseEntity<Employee>(employee1, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(
            @PathVariable long id
    ) throws RecordNotFoundException {
        Employee employee =  employeeService.getEmployeeById(id);

        return new ResponseEntity<Employee>(employee, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<Page<Employee>> getAllEmployees(Pageable pageable) throws RecordNotFoundException {
        Page<Employee> employees = employeeService.getAllEmployees(pageable);

        return new ResponseEntity<Page<Employee>>(employees, new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping("/{id}/skills")
    public ResponseEntity<Page<Skills>> getAllSkills(
            @PathVariable long id,Pageable pageable) {

        Page<Skills> skillsList = skillsService.findByEmployeeId(id,pageable);

        return new ResponseEntity<Page<Skills>>(skillsList, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/{id}/skills")
    public ResponseEntity<Skills> saveSkills(
            @PathVariable long id,
            @RequestBody SkillsInput input
    ) throws RecordNotFoundException {
        Skills skills1 = skillsService.saveSkills(input,id,null);
        return new ResponseEntity<Skills>(skills1, new HttpHeaders(), HttpStatus.OK);
    }


    @PostMapping("/{employeeId}/skills/{skillsId}")
    public ResponseEntity<Skills> updateSkills(
            @PathVariable long employeeId,
            @RequestBody SkillsInput input,
            @PathVariable Long skillsId
    ) throws RecordNotFoundException {
        Skills skills1 = skillsService.saveSkills(input,employeeId,skillsId);
        return new ResponseEntity<Skills>(skills1, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable long id
    ) throws RecordNotFoundException {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee successfully deleted");
    }


    @DeleteMapping("/{id}/skills/{skillId}")
    public ResponseEntity<String> deleteSkill(
            @PathVariable Long id,@PathVariable long skillId
    ) throws RecordNotFoundException {
        skillsService.deleteSkill(id,skillId);
        return ResponseEntity.ok("Skill successfully deleted");
    }
}
