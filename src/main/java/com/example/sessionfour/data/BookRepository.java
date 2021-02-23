package com.example.sessionfour.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.sessionfour.models.Book;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
	Page<Book> findAllByAuthorId(Long authorId, Pageable pageable);
}
