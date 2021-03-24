package com.robertw.EmployeeSkillsTracker.controller;

import com.robertw.EmployeeSkillsTracker.exception.EmployeeNotFoundException;
import com.robertw.EmployeeSkillsTracker.exception.SkillNotFoundException;
import com.robertw.EmployeeSkillsTracker.model.Employee;
import com.robertw.EmployeeSkillsTracker.model.Skill;
import com.robertw.EmployeeSkillsTracker.service.interfaces.EmployeeService;
import com.robertw.EmployeeSkillsTracker.service.interfaces.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employee/{employeeId}/skill")
public class SkillController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SkillService skillService;

    public SkillController(EmployeeService employeeService, SkillService skillService) {
        this.employeeService = employeeService;
        this.skillService = skillService;
    }

    @GetMapping
    public ResponseEntity<Page<Skill>> getAllSkills(@PathVariable long employeeId, Pageable page)
            throws EmployeeNotFoundException {
        Employee employee = this.employeeService.getEmployeeById(employeeId);
        Page<Skill> skills = this.skillService.getAllSkills(employee, page);

        return ResponseEntity.ok(skills);
    }

    @GetMapping("{skillId}")
    public ResponseEntity<Skill> getSkillById(@PathVariable long employeeId, @PathVariable long skillId)
            throws EmployeeNotFoundException, SkillNotFoundException {
        Employee employee = this.employeeService.getEmployeeById(employeeId);
        Skill skill = this.skillService.getSkillById(employee, skillId);

        return ResponseEntity.ok(skill);
    }

    @PostMapping
    public ResponseEntity<Skill> createOrUpdateSkill(@PathVariable long employeeId, @RequestBody Skill skill) throws EmployeeNotFoundException, SkillNotFoundException {
        Employee employee = this.employeeService.getEmployeeById(employeeId);
        skill.setEmployee(employee);
        Skill updatedSkill = this.skillService.createOrUpdateSkill(employee, skill);

        return ResponseEntity.ok(updatedSkill);
    }

    @DeleteMapping("{skillId}")
    public ResponseEntity<String> deleteSkill(@PathVariable long employeeId, @PathVariable long skillId)
            throws SkillNotFoundException, EmployeeNotFoundException {
        Employee employee = this.employeeService.getEmployeeById(employeeId);
        this.skillService.deleteSkillById(employee, skillId);

        return ResponseEntity.ok("Skill deleted");
    }
}
