package com.mattf.employeeskills.service;

import com.mattf.employeeskills.entity.*;
import com.mattf.employeeskills.exception.EmployeeNotFoundException;
import com.mattf.employeeskills.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SkillService {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    SkillRepository repository;

    public Skill createSkill(Skill skill, Long employeeId)
            throws EmployeeNotFoundException {
        Employee employee = employeeService.getByEmployeeId(employeeId);

        skill.setEmployee(employee);
        return repository.saveAndFlush(skill);
    }

    public Skill updateSkill(Skill skill) {
        return repository.saveAndFlush(skill);
    }

    public void deleteSkill(Long skillId){
        repository.deleteById(skillId);
    }

    public Optional<Skill> getSkillById(Long skillId) {
        return repository.findById(skillId);
    }

    public Page<Skill> getAllSkillsByEmployee(Long employeeId, Pageable pageable) throws EmployeeNotFoundException {
        Employee employee = employeeService.getByEmployeeId(employeeId);
        Page<Skill> skills = repository.findAllByEmployeeId(employeeId, pageable);
        return skills;
    }
}
