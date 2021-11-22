package tests;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.List;

import entities.BookAuthor;
import exceptions.service.bookauthor.BookAuthorNotFoundException;
import exceptions.service.bookauthor.InvalidBookAuthorAuthorIdException;
import exceptions.service.bookauthor.InvalidBookAuthorBookIdException;
import services.BookAuthorService;

public class BookAuthorServiceTests {
	BookAuthorService bookAuthorService;
	
	public BookAuthorServiceTests() {
		bookAuthorService = new BookAuthorService();
	}
	
	@Test
	public void given_valid_parameters_should_add_book_author() {
		Integer bookId = 1;
		Integer authorId = 1;
		BookAuthor bookAuthor = BookAuthor.create(bookId, authorId);
		Integer expectedId = 1;
		
		Integer id = bookAuthorService.add(bookAuthor);
		
		assertThat(id).isEqualTo(expectedId);		
	}
	
	@Test
	public void given_invalid_author_id_when_add_should_throw_an_exception() {
		Integer bookId = 1;
		Integer authorId = 0;
		BookAuthor bookAuthor = BookAuthor.create(bookId, authorId);
		InvalidBookAuthorAuthorIdException expectedException = new InvalidBookAuthorAuthorIdException(bookAuthor.id, authorId);
		
		InvalidBookAuthorAuthorIdException thrown = (InvalidBookAuthorAuthorIdException) catchThrowable(() -> bookAuthorService.add(bookAuthor));
		
		assertThat(thrown).isInstanceOf(expectedException.getClass());
		assertThat(thrown.getMessage()).isEqualTo(expectedException.getMessage());
		assertThat(thrown.bookAuthorId).isNull();
		assertThat(thrown.authorId).isEqualTo(authorId);	
	}
	
	@Test
	public void given_invalid_book_id_when_add_should_throw_an_exception() {
		Integer bookId = 0;
		Integer authorId = 1;
		BookAuthor bookAuthor = BookAuthor.create(bookId, authorId);
		InvalidBookAuthorBookIdException expectedException = new InvalidBookAuthorBookIdException(bookAuthor.id, bookId);
		
		InvalidBookAuthorBookIdException thrown = (InvalidBookAuthorBookIdException) catchThrowable(() -> bookAuthorService.add(bookAuthor));
		
		assertThat(thrown).isInstanceOf(expectedException.getClass());
		assertThat(thrown.getMessage()).isEqualTo(expectedException.getMessage());
		assertThat(thrown.bookAuthorId).isNull();
		assertThat(thrown.bookId).isEqualTo(bookId);	
	}
	
	@Test
	public void given_valid_parameters_should_update_book_author() {
		Integer bookId = 1;
		Integer authorId = 1;
		Integer bookIdAfterUpdate = 2;
		Integer id = bookAuthorService.add(BookAuthor.create(bookId, authorId));
		
		BookAuthor bookAuthorAdded = bookAuthorService.getById(id);
		bookAuthorAdded.bookId = bookIdAfterUpdate;
		bookAuthorService.update(bookAuthorAdded);
		BookAuthor bookAuthorUpdated = bookAuthorService.getById(id);
		
		assertThat(bookAuthorUpdated.bookId).isEqualTo(bookIdAfterUpdate);		
	}
	
	@Test
	public void given_invalid_book_id_when_update_should_throw_an_exception() {
		Integer bookId = 1;
		Integer authorId = 1;
		Integer invalidAuthorId = 0;
		Integer id = bookAuthorService.add(BookAuthor.create(bookId, authorId));
		InvalidBookAuthorAuthorIdException expectedException = new InvalidBookAuthorAuthorIdException(id, invalidAuthorId);
				
		BookAuthor bookAuthor = bookAuthorService.getById(id);
		bookAuthor.authorId = invalidAuthorId;
		InvalidBookAuthorAuthorIdException thrown = (InvalidBookAuthorAuthorIdException) catchThrowable(() -> bookAuthorService.update(bookAuthor));
		
		assertThat(thrown).isInstanceOf(expectedException.getClass());
		assertThat(thrown.getMessage()).isEqualTo(expectedException.getMessage());
		assertThat(thrown.bookAuthorId).isEqualTo(id);
		assertThat(thrown.authorId).isEqualTo(invalidAuthorId);		
	}
	
	@Test
	public void given_invalid_author_id_when_update_should_throw_an_exception() {
		Integer bookId = 1;
		Integer authorId = 1;
		Integer invalidBookId = 0;
		Integer id = bookAuthorService.add(BookAuthor.create(bookId, authorId));
		InvalidBookAuthorBookIdException expectedException = new InvalidBookAuthorBookIdException(id, invalidBookId);
				
		BookAuthor bookAuthor = bookAuthorService.getById(id);
		bookAuthor.bookId = invalidBookId;
		InvalidBookAuthorBookIdException thrown = (InvalidBookAuthorBookIdException) catchThrowable(() -> bookAuthorService.update(bookAuthor));
		
		assertThat(thrown).isInstanceOf(expectedException.getClass());
		assertThat(thrown.getMessage()).isEqualTo(expectedException.getMessage());
		assertThat(thrown.bookAuthorId).isEqualTo(id);
		assertThat(thrown.bookId).isEqualTo(invalidBookId);
	}
	
	@Test
	public void given_valid_parameters_should_delete_book_author() {
		Integer bookId = 1;
		Integer authorId = 1;
		Integer id = bookAuthorService.add(BookAuthor.create(bookId, authorId));
		int expectedSize = 0;
		
		bookAuthorService.delete(id);
		List<BookAuthor> bookAuthors = bookAuthorService.getEntities();
		
		assertThat(bookAuthors.size()).isEqualTo(expectedSize);		
	}
	
	@Test
	public void given_invalid_parameters_should_throw_an_exception() {
		Integer bookAuthorId = 1;
		BookAuthorNotFoundException expectedException = new BookAuthorNotFoundException(bookAuthorId);
			
		BookAuthorNotFoundException thrown = (BookAuthorNotFoundException) catchThrowable(() -> bookAuthorService.delete(bookAuthorId));
		
		assertThat(thrown).isInstanceOf(expectedException.getClass());
		assertThat(thrown.getMessage()).isEqualTo(expectedException.getMessage());
		assertThat(thrown.bookAuthorId).isEqualTo(bookAuthorId);		
	}
}
