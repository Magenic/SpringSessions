package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Transaction;



@Repository
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {

	Page<Transaction> findByAccountId(Long accountId ,Pageable page);
	List<Transaction> findByAccountId(Long accountId);
}
