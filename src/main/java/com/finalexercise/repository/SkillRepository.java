package com.finalexercise.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.finalexercise.model.Skill;


@Repository
public interface SkillRepository extends PagingAndSortingRepository<Skill, Long> {
	Page<Skill> findAllByEmployeeId(Long employeeId, Pageable pageable);
}
