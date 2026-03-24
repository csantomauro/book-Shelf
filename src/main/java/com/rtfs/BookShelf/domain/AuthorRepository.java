package com.rtfs.BookShelf.domain;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
	Optional<Author> findByFirstname(String firstName);
}
