package com.example.sessionfour.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sessionfour.exceptions.RecordNotFoundException;
import com.example.sessionfour.models.Author;
import com.example.sessionfour.models.Book;
import com.example.sessionfour.models.CreateBookRequest;
import com.example.sessionfour.services.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {

	@Autowired
	private AuthorService service;
	
	@GetMapping(params = { "page", "size", "sort", "dir" })
	public ResponseEntity<Page<Author>> get(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("sort") String sort, @RequestParam("dir") String dir) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Direction.fromString(dir), sort));
		return new ResponseEntity<Page<Author>>(service.get(pageRequest), new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Author> get(@PathVariable("id") Long id) throws RecordNotFoundException {
		return new ResponseEntity<Author>(service.get(id), new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Author> save(@RequestBody Author author) {
		return new ResponseEntity<Author>(service.create(author), new HttpHeaders(), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Author> save(@PathVariable("id") Long id, @RequestBody Author author) throws RecordNotFoundException {
		return new ResponseEntity<Author>(service.update(id, author), new HttpHeaders(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws RecordNotFoundException {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(path = "/{id}/books", params = { "page", "size", "sort", "dir" })
	public ResponseEntity<Page<Book>> getBooks(@PathVariable("id") Long id, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("sort") String sort, @RequestParam("dir") String dir) throws RecordNotFoundException {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Direction.fromString(dir), sort));
		return new ResponseEntity<Page<Book>>(service.getBooks(id, pageRequest), new HttpHeaders(), HttpStatus.OK);
	}
	
	@PostMapping("/{id}/books")
	public ResponseEntity<Book> saveBook(@PathVariable("id") Long id, @RequestBody CreateBookRequest book) throws RecordNotFoundException {
		return new ResponseEntity<Book>(service.createBook(id, book), new HttpHeaders(), HttpStatus.CREATED);
	}
}