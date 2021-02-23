package com.example.sessionfour.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.sessionfour.models.Author;

@Repository
public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {
	Page<Author> findAll(Pageable pageable);
}
