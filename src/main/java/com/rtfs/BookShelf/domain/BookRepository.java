package com.rtfs.BookShelf.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported=false)
public interface BookRepository extends CrudRepository<Book,Long> {
	List<Book> findByTitle(@Param("title") String title);
	
	List<Book> findByGenre(@Param("genre") String genre);
}
