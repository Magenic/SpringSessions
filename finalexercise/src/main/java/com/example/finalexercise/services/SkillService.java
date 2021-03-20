package com.example.finalexercise.services;

import com.example.finalexercise.data.SkillRepository;
import com.example.finalexercise.exceptions.EmployeeNotFoundException;
import com.example.finalexercise.exceptions.SkillNotFoundException;
import com.example.finalexercise.models.Employee;
import com.example.finalexercise.models.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SkillService {
    @Autowired
    SkillRepository skillRepository;

    @Autowired
    EmployeeService employeeService;

    public Page<Skill> get(Pageable pageable, Long employeeId) throws EmployeeNotFoundException {
        Employee employee = this.employeeService.get(employeeId);
        return skillRepository.findAllByEmployeeId(employeeId, pageable);
    }

    public Skill get(Long id) throws SkillNotFoundException {
        Optional<Skill> skill = skillRepository.findById(id);

        if (skill.isPresent()) {
            return skill.get();
        } else {
            throw new SkillNotFoundException("Skill not found.");
        }
    }

    public Skill create(Skill request, Long employeeId) throws EmployeeNotFoundException {
        Employee employee = employeeService.get(employeeId);
        Skill skill = new Skill();
        skill.setDescription(request.getDescription());
        skill.setDuration(request.getDuration());
        skill.setLastUsed(request.getLastUsed());
        skill.setEmployeeId(employeeId);
        return skillRepository.save(skill);
    }

    public Skill update(Skill request, Long employeeId, Long skillId) throws EmployeeNotFoundException, SkillNotFoundException {
        Skill skill = this.get(skillId);
        Employee employee = employeeService.get(employeeId);
        skill.setDescription(request.getDescription());
        skill.setDuration(request.getDuration());
        skill.setLastUsed(request.getLastUsed());
        return skillRepository.save(skill);
    }

    public void delete(Long employeeId, Long skillId) throws SkillNotFoundException, EmployeeNotFoundException {
        Employee employee = this.employeeService.get(employeeId);
        Optional<Skill> skillOptional = skillRepository.findById(skillId);

        if (!skillOptional.isPresent()) {
            throw new SkillNotFoundException("Skill not found.");
        }
        Skill skill = skillOptional.get();
        if (skill.getEmployeeId() != employeeId) {
            String message = "Employee id {} does not match skill employee id {}";
            throw new SkillNotFoundException(String.format(message, employeeId, skill.getEmployeeId()));
        }
        skillRepository.delete(skill);
    }
}
