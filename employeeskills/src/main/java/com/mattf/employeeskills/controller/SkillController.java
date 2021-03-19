package com.mattf.employeeskills.controller;

import com.mattf.employeeskills.entity.*;
import com.mattf.employeeskills.exception.EmployeeNotFoundException;
import com.mattf.employeeskills.exception.SkillNotFoundException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("api/employees/{id}/skills")
public class SkillController {
    @Autowired
    com.mattf.employeeskills.service.SkillService service;

    @GetMapping
    public ResponseEntity<Page<Skill>> getAllSkills(@PathVariable Long id, Pageable pageable) throws EmployeeNotFoundException {
        Page<Skill> transactions = service.getAllSkillsByEmployee(id, pageable);
        return new ResponseEntity<>(transactions, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Skill> addSkill(@PathVariable Long id, @RequestBody Skill skill) throws EmployeeNotFoundException {
        Skill savedSkill = service.createSkill(skill, id);
        return new ResponseEntity<>(savedSkill, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/{skillId}")
    public ResponseEntity<Skill> updateSkill(@PathVariable Long skillId, @RequestBody Skill skill) throws SkillNotFoundException {
        skillExists(skillId);
        Skill updatedSkill = service.updateSkill(skill);
        return new ResponseEntity<>(updatedSkill, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/{skillId}")
    public ResponseEntity<String> deleteSkill(@PathVariable Long skillId) throws SkillNotFoundException {
        skillExists(skillId);
        service.deleteSkill(skillId);
        return new ResponseEntity<>("Delete skill completed successfully", new HttpHeaders(), HttpStatus.OK);
    }

    private void skillExists(Long skillId) throws SkillNotFoundException {
        Optional<Skill> transactionFromDb = service.getSkillById(skillId);
        if (!transactionFromDb.isPresent()) {
            throw new SkillNotFoundException();
        }
    }
}
