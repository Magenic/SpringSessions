package com.example.sessionfour.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.sessionfour.data.AuthorRepository;
import com.example.sessionfour.data.BookRepository;
import com.example.sessionfour.exceptions.RecordNotFoundException;
import com.example.sessionfour.models.Author;
import com.example.sessionfour.models.Book;
import com.example.sessionfour.models.CreateBookRequest;

@Service
public class AuthorService {
	@Autowired
	private AuthorRepository repository;
	@Autowired
	private BookRepository bookRepository;
	
	public Page<Author> get(Pageable pageRequest) {
		return repository.findAll(pageRequest);
	}
	
	public Author get(Long id) throws RecordNotFoundException {
		Optional<Author> author = repository.findById(id);
		
		if (author.isPresent()) {
			return author.get();
		} else {
			throw new RecordNotFoundException("Author not found.");
		}
	}
	
	public Author create(Author author) {
		return repository.save(author);
	}
	
	public Author update(Long id, Author author) throws RecordNotFoundException {
		Optional<Author> existingAuthor = repository.findById(id);
		
		if (existingAuthor.isPresent()) {
			Author a = existingAuthor.get();
			a.setName(author.getName());
			
			return repository.save(a);
		} else {
			throw new RecordNotFoundException("Author not found.");
		}
	}
	
	public void delete(Long id) throws RecordNotFoundException {
		Optional<Author> author = repository.findById(id);
		
		if (author.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("Author not found.");
		}
	}
	
	public Page<Book> getBooks(Long id, Pageable pageRequest) throws RecordNotFoundException {
		return bookRepository.findAllByAuthorId(id, pageRequest);
	}
	
	public Book createBook(Long authorId, CreateBookRequest request) throws RecordNotFoundException {
		Optional<Author> authorRecord = repository.findById(authorId);
		if (!authorRecord.isPresent()) {
			throw new RecordNotFoundException("Author not found.");
		}
		Author author = authorRecord.get();
		
		Book book = new Book();
		book.setTitle(request.getTitle());
		book.setDescription(request.getDescription());
		book.setAuthorId(authorId);
		book = bookRepository.save(book);
		
		List<Book> books = author.getBooks();
		if (books == null) {
			books = new ArrayList<Book>();
		}
		books.add(book);
		author.setBooks(books);
		
		author = repository.save(author);
		return book;
	}
}
