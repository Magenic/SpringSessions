package com.magenic.springboot.finals.service.impl;

import com.magenic.springboot.finals.exception.RecordNotFoundException;
import com.magenic.springboot.finals.models.Employee;
import com.magenic.springboot.finals.models.Skills;
import com.magenic.springboot.finals.models.SkillsInput;
import com.magenic.springboot.finals.repository.EmployeeRepository;
import com.magenic.springboot.finals.repository.SkillsRepository;
import com.magenic.springboot.finals.service.SkillsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;

@Component
public class SkillsServiceImpl implements SkillsService {

    private SkillsRepository skillsRepository;
    private EmployeeRepository employeeRepository;

    public SkillsServiceImpl(SkillsRepository skillsRepository, EmployeeRepository employeeRepository) {
        this.skillsRepository = skillsRepository;
        this.employeeRepository = employeeRepository;
    }

    public Skills saveSkills(SkillsInput input, Long employeeId,Long skillsId)  throws RecordNotFoundException{
        Skills skills = new Skills();
        Employee employee = new Employee();
        if(employeeId != null){
            Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
            if(employeeOptional.isPresent()){
                employee = employeeOptional.get();
            }else{
                throw new RecordNotFoundException("Employee with ID " + employeeId + " does not exists");
            }
        }else{
            throw new RecordNotFoundException("Employee with ID " + employeeId + " does not exists");
        }
        if(skillsId != null){
            Optional<Skills> skillsOptional = skillsRepository.findById(skillsId);
            if(skillsOptional.isPresent()){
                skills = skillsOptional.get();
            }
        }
        skills.setEmployee(employee);
        skills.setLastUsed(input.getLastUsed());
        skills.setDuration(input.getDuration());
        skills.setDescription(input.getDescription());
        return skillsRepository.save(skills);
    }

    @Override
    public void deleteSkill(Long employeeId, Long skillsId) throws RecordNotFoundException {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if(!employeeOptional.isPresent()){
            throw new RecordNotFoundException("Employee with ID " + employeeId + " does not exists");
        }
        Optional<Skills> skillsOptional = skillsRepository.findById(skillsId);
        if(skillsOptional.isPresent()){
            skillsRepository.delete(skillsOptional.get());
        }else{
            throw new RecordNotFoundException("Skills with ID " + skillsId + " does not exists");
        }
    }

    @Override
    public Page<Skills> findByEmployeeId(Long id, Pageable pageable) {
        Page<Skills> skills = skillsRepository.findAll(pageable);
        return skills;
    }
}
