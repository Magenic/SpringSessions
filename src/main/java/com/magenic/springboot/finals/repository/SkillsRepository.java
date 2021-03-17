package com.magenic.springboot.finals.repository;

import com.magenic.springboot.finals.models.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsRepository extends JpaRepository<Skills,Long> {
}
