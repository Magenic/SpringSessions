package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.InterestAccount;

@Repository
public interface InterestAccountRepository extends JpaRepository<InterestAccount, Long> {
	
	 @Query("select a from InterestAccount a where a.balance > 0")
	    List<InterestAccount> findAllWithUpdatedDateBefore();
}
