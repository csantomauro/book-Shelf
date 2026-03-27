package com.rtfs.BookShelf.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rtfs.BookShelf.domain.Book;
import com.rtfs.BookShelf.domain.BookRepository;

@RestController
public class BookController {
	private final BookRepository repository;
	
	public BookController(BookRepository repository) {
		this.repository = repository;
	}
	@GetMapping("/books")
	public Iterable<Book> getBooks() {
		return repository.findAll();
	}
	@GetMapping("/health")
	public ResponseEntity<String> health() {
	    return ResponseEntity.ok("ok");
	}
}
