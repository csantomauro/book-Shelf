package com.rtfs.BookShelf;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rtfs.BookShelf.domain.AppUser;
import com.rtfs.BookShelf.domain.AppUserRepository;
import com.rtfs.BookShelf.domain.Author;
import com.rtfs.BookShelf.domain.AuthorRepository;
import com.rtfs.BookShelf.domain.Book;
import com.rtfs.BookShelf.domain.BookRepository;

@SpringBootApplication
public class BookShelfApplication implements CommandLineRunner {

		private static final Logger logger = LoggerFactory.getLogger(BookShelfApplication.class);

		private final BookRepository repository;
		private final AuthorRepository orepository;
		private final AppUserRepository urepository;

		public BookShelfApplication(BookRepository repository, AuthorRepository orepository, AppUserRepository urepository) {
			this.repository = repository;
			this.orepository = orepository;
			this.urepository = urepository;
		}

		public static void main(String[] args) {
			SpringApplication.run(BookShelfApplication.class, args);
		}

		@Override
		public void run(String... args) throws Exception {
			if (urepository.count() > 0) return;
			// Add owner objects and save these to db
			// Add owner objects and save these to db
			Author author1 = new Author("Fyodor", "Dostoevsky");
			Author author2 = new Author("Haruki", "Murakami");
			Author author3 = new Author("Dante", "Alighieri");
			orepository.saveAll(Arrays.asList(author1, author2, author3));
			repository.save(new Book(
			        "Crime and Punishment",
			        "Psychological Fiction",
			        "978-0140449136",
			        "Penguin Classics",
			        1866,
			        15,
			        author1
			));

			repository.save(new Book(
			        "La Divina Commedia",
			        "Epic Poetry",
			        "978-0142437223",
			        "Oxford University Press",
			        1320,
			        18,
			        author1
			));

			repository.save(new Book(
			        "Norwegian Wood",
			        "Literary Fiction",
			        "978-0375704024",
			        "Vintage",
			        1987,
			        14,
			        author2
			));
			
			// Fetch all book and log to console
			for (Book book : repository.findAll()) {
				logger.info("brand: {}, model: {}", book.getTitle(), book.getGenre());
			}
			
			// Username: user, password: user
			urepository.save(new AppUser("user", "$2a$10$NVM0n8ElaRgg7zWO1CxUdei7vWoPg91Lz2aYavh9.f9q0e4bRadue", "USER"));
			// Username: admin, password: admin
			urepository.save(new AppUser("admin","$2a$10$8cjz47bjbR4Mn8GMg9IZx.vyjhLXR/SKKMSZ9.mP9vpMu0ssKi8GW", "ADMIN"));
			
		}

}
