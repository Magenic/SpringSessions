package com.robertw.EmployeeSkillsTracker.service.interfaces;

import com.robertw.EmployeeSkillsTracker.exception.SkillNotFoundException;
import com.robertw.EmployeeSkillsTracker.model.Employee;
import com.robertw.EmployeeSkillsTracker.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SkillService {
    Page<Skill> getAllSkills(Employee employee, Pageable page);
    Skill getSkillById(Employee employee, long skillId) throws SkillNotFoundException;
    Skill createOrUpdateSkill(Employee employee, Skill skill) throws SkillNotFoundException;
    void deleteSkillById(Employee employee, long skillId) throws SkillNotFoundException;
}
