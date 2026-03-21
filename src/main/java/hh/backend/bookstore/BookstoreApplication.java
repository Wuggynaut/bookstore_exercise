package hh.backend.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.backend.bookstore.domain.AppUser;
import hh.backend.bookstore.domain.Book;
import hh.backend.bookstore.domain.BookRepository;
import hh.backend.bookstore.domain.Category;
import hh.backend.bookstore.domain.CategoryRepository;
import hh.backend.bookstore.domain.AppUserRepository;

@SpringBootApplication
public class BookstoreApplication {

	private final AppUserRepository appUserRepository;
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	BookstoreApplication(AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner exampleData(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			log.info("save example books and categories");

			Category scifi = new Category("Science Fiction");
			categoryRepository.save(scifi);
			Category litfic = new Category("Literary Fiction");
			categoryRepository.save(litfic);
			Category fantasy = new Category("Fantasy");
			categoryRepository.save(fantasy);

			bookRepository.save(new Book("A Door Into Ocean", "Joan Slonczewski", 1986, "9780312876524", 17.85, scifi));
			bookRepository.save(new Book("Alien Clay", "Adrian Tchaikovsky", 2024, "9780316578974", 17.25, scifi));
			bookRepository.save(new Book("The Metamorphosis", "Franz Kafka", 1915, "9780553213690", 9.40, litfic));

			AppUser user1 = new AppUser("user", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
					"user@email.com", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
					"admin@email.com", "ADMIN");
			appUserRepository.save(user1);
			appUserRepository.save(user2);

			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
			log.info("fetch all categories");
			for (Category category : categoryRepository.findAll()) {
				log.info(category.toString());
			}
		};
	}

}
