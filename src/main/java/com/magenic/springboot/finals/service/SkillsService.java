package com.magenic.springboot.finals.service;

import com.magenic.springboot.finals.exception.RecordNotFoundException;
import com.magenic.springboot.finals.models.Employee;
import com.magenic.springboot.finals.models.Skills;
import com.magenic.springboot.finals.models.SkillsInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface SkillsService {

    public Skills saveSkills(SkillsInput input, Long employeeId,Long skillsId) throws RecordNotFoundException;
    public void deleteSkill(Long employeeId,Long skillsId) throws RecordNotFoundException;
    Page<Skills> findByEmployeeId(Long id, Pageable pageable);
}
