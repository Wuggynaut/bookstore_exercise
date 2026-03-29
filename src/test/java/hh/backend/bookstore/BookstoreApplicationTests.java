package hh.backend.bookstore;

import hh.backend.bookstore.domain.*;
import hh.backend.bookstore.web.BookController;
import hh.backend.bookstore.web.CategoryController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class BookstoreApplicationTests {

	@Autowired
	private AppUserRepository userRepository;
	@Autowired
	private BookController bookController;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CategoryController categoryController;
	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	void bookControllerLoads() {
		assertThat(bookController).isNotNull();
	}

	@Test
	void categoryControllerLoads() {
		assertThat(categoryController).isNotNull();
	}

	// Book tests

	@Test
	void createNewBook() {
		Book book = new Book("Testbook", "Testauthor", 1992, "AAA", 10.10, null);
		bookRepository.save(book);
		assertThat(book.getId()).isNotNull();
	}

	@Test
	void deleteBook() {
		Book book = new Book("DeleteMe", "Author", 2000, "BBB", 5.00, null);
		bookRepository.save(book);
		Long id = book.getId();
		bookRepository.deleteById(id);
		assertThat(bookRepository.findById(id)).isEmpty();
	}

	@Test
	void findBookById() {
		Book book = new Book("Findable", "Author", 2010, "CCC", 15.00, null);
		bookRepository.save(book);
		Book found = bookRepository.findById(book.getId()).orElse(null);
		assertThat(found).isNotNull();
		assertThat(found.getTitle()).isEqualTo("Findable");
	}

	// User tests

	@Test
	void createNewUser() {
		AppUser user = new AppUser("Test-User", "testpasshash", "test@email.com", "USER");
		userRepository.save(user);
		assertThat(user.getId()).isNotEqualTo(0);
	}

	@Test
	void deleteUser() {
		AppUser user = new AppUser("DeleteUser", "testpasshash", "delete@email.com", "USER");
		userRepository.save(user);
		Long id = user.getId();
		userRepository.deleteById(id);
		assertThat(userRepository.findById(id)).isEmpty();
	}

	@Test
	void findUserByUsername() {
		AppUser user = new AppUser("FindableUser", "testpasshash", "find@email.com", "USER");
		userRepository.save(user);
		AppUser found = userRepository.findByUsername("FindableUser");
		assertThat(found).isNotNull();
		assertThat(found.getEmail()).isEqualTo("find@email.com");
	}

	// Category tests

	@Test
	void createNewCategory() {
		Category category = new Category("Testcategory");
		categoryRepository.save(category);
		assertThat(category.getCategoryid()).isNotNull();
	}

	@Test
	void deleteCategory() {
		Category category = new Category("DeleteMe");
		categoryRepository.save(category);
		Long id = category.getCategoryid();
		categoryRepository.deleteById(id);
		assertThat(categoryRepository.findById(id)).isEmpty();
	}

	@Test
	void findCategoryById() {
		Category category = new Category("Findable");
		categoryRepository.save(category);
		Category found = categoryRepository.findById(category.getCategoryid()).orElse(null);
		assertThat(found).isNotNull();
		assertThat(found.getName()).isEqualTo("Findable");
	}

}
