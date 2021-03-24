package com.robertw.EmployeeSkillsTracker.service;

import com.robertw.EmployeeSkillsTracker.exception.SkillNotFoundException;
import com.robertw.EmployeeSkillsTracker.model.Employee;
import com.robertw.EmployeeSkillsTracker.model.Skill;
import com.robertw.EmployeeSkillsTracker.repository.SkillRepository;
import com.robertw.EmployeeSkillsTracker.service.interfaces.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Page<Skill> getAllSkills(Employee employee, Pageable page) {
        return this.skillRepository.findSkillsByEmployee(page, employee);
    }

    @Override
    public Skill getSkillById(Employee employee, long skillId) throws SkillNotFoundException {
        return this.skillRepository.findSkillById(employee, skillId)
                .orElseThrow(SkillNotFoundException::new);
    }

    @Override
    public Skill createOrUpdateSkill(Employee employee, Skill skill)  {
        Skill skillToCreateOrUpdate;
        Optional<Skill> skillFound = this.skillRepository.findSkillById(employee, skill.getId());

        if (skillFound.isPresent()) {
            skillToCreateOrUpdate = skillFound.get();
            skillToCreateOrUpdate.setDescription(skill.getDescription());
            skillToCreateOrUpdate.setTitle(skill.getTitle());
            skillToCreateOrUpdate.setEmployee(skill.getEmployee());
            skillToCreateOrUpdate.setLastUsed(skill.getLastUsed());
            skillToCreateOrUpdate.setDuration(skill.getDuration());
        }
        else {
            skillToCreateOrUpdate = skill;
        }

        Skill newSkill = this.skillRepository.save(skillToCreateOrUpdate);
        return newSkill;
    }

    @Override
    public void deleteSkillById(Employee employee, long skillId) throws SkillNotFoundException {
        Skill skillToDelete = this.skillRepository.findSkillById(employee, skillId)
                .orElseThrow(SkillNotFoundException::new);

        this.skillRepository.delete(skillToDelete);
    }
}
