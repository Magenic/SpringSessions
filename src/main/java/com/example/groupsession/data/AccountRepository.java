package com.example.groupsession.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.groupsession.models.BaseAccount;

@Repository
public interface AccountRepository extends JpaRepository<BaseAccount, Long> { }