package com.annusza.tau.lab.myBookApp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.annusza.tau.lab.myBookApp.domain.Book;
import com.annusza.tau.lab.myBookApp.service.BookManagerImpl;

@RunWith(JUnit4.class)
public class BookManagerTest {

	BookManagerImpl bookManagerImpl = new BookManagerImpl();

	@Test
	public void dummyTest() {

		Assert.assertTrue(true);

	}

	@Test
	public void getBookByIdShouldReturnBookWithIndicatedId() throws Exception {

		Book persepolis = new Book();
		persepolis.setId(1);
		persepolis.setAuthorName("Marjane");
		persepolis.setAuthorSurname("Satrapi");
		persepolis.setTitle("Persepolis");
		persepolis.setYearOfPublication(2005);

		bookManagerImpl.addBook(persepolis);

		assertEquals(bookManagerImpl.getBookById(1).getId(), persepolis.getId());
		assertEquals(persepolis.getTitle(), bookManagerImpl.getBookById(1).getTitle());

	}

	@Test
	public void addBookShouldAddNewBook() throws Exception {

		Book maus = new Book();
		maus.setId(2);
		maus.setAuthorName("Art");
		maus.setAuthorSurname("Spiegelman");
		maus.setTitle("Maus");
		maus.setYearOfPublication(2009);

		bookManagerImpl.addBook(maus);

		assertEquals(bookManagerImpl.getBookById(2).getTitle(), maus.getTitle());

	}

	@Test
	public void deleteBookShouldDeleteIndicatedBook() throws Exception {

		final int bookId = 3;
		Book howl = new Book();
		howl.setId(bookId);
		howl.setAuthorName("Diane Wynne");
		howl.setAuthorSurname("Jones");
		howl.setTitle("Howl's Moving Castle");
		howl.setYearOfPublication(1990);

		bookManagerImpl.addBook(howl);
		bookManagerImpl.deleteBook(bookId);

		assertNull(bookManagerImpl.getBookById(bookId));

	}

	@Test
	public void updateBookShouldChangeIndicatedBook() throws Exception {

		Book macbeth = new Book();
		final int bookId = 1;
		macbeth.setId(bookId);
		macbeth.setAuthorName("William");
		macbeth.setAuthorSurname("Shakespeare");
		macbeth.setTitle("Hamlet");
		macbeth.setYearOfPublication(1970);
		bookManagerImpl.addBook(macbeth);

		Book bookToUpdate = bookManagerImpl.getBookById(bookId);
		
		
		bookToUpdate.setTitle("Macbeth");
		macbeth.setTitle(bookToUpdate.getTitle());
		bookManagerImpl.updateBook(macbeth);

		assertEquals( bookManagerImpl.getBookById(1).getTitle(), bookToUpdate.getTitle());
	}

	

}
