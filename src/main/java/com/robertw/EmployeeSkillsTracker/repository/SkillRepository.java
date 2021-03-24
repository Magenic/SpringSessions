package com.robertw.EmployeeSkillsTracker.repository;

import com.robertw.EmployeeSkillsTracker.exception.SkillNotFoundException;
import com.robertw.EmployeeSkillsTracker.model.Employee;
import com.robertw.EmployeeSkillsTracker.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    @Query("select s from Skill s where s.employee = :employee")
    Page<Skill> findSkillsByEmployee(Pageable page, @Param("employee") Employee employee);

    @Query("select s from Skill s where s.employee = :employee and s.id = :skillId")
    Optional<Skill> findSkillById(@Param("employee") Employee employee, @Param("skillId")long skillId);
}
