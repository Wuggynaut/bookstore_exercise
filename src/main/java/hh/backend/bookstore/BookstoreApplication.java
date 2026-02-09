package hh.backend.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.backend.bookstore.domain.Book;
import hh.backend.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner exampleData(BookRepository bookRepository) {
		return (args) -> {
			bookRepository.save(new Book("A Door Into Ocean", "Joan Slonczewski", 1986, "9780312876524", 17.85));
			bookRepository.save(new Book("Alien Clay", "Adrian Tchaikovsky", 2024, "9780316578974", 17.25));
			bookRepository.save(new Book("The Metamorphosis", "Franz Kafka", 1915, "9780553213690", 9.40));
		};
	}

}
