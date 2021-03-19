package com.mattf.employeeskills.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<com.mattf.employeeskills.entity.Skill, Long> {
    Page<com.mattf.employeeskills.entity.Skill> findAllByEmployeeId(Long employeeId, Pageable pageable);
}
