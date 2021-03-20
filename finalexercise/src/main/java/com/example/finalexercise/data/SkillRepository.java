package com.example.finalexercise.data;

import com.example.finalexercise.models.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends PagingAndSortingRepository<Skill, Long> {
    Page<Skill> findAllByEmployeeId(Long id, Pageable pageable);
}
