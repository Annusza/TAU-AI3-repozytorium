package com.annusza.tau.lab01.myBookApp.test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.annusza.tau.lab01.myBookApp.domain.Book;
import com.annusza.tau.lab01.myBookApp.service.BookManagerImpl;

@RunWith(JUnit4.class)
public class BookManagerTest {

	BookManagerImpl bookManagerImpl = new BookManagerImpl();

	@Test
	public void dummyTest() {

		Assert.assertTrue(true);

	}

	@Test
	public void getBookByIdShouldReturnBookWithIndicatedId() {

		Book persepolis = new Book();
		persepolis.setId(1);
		persepolis.setAuthorName("Marjane");
		persepolis.setAuthorSurname("Satrapi");
		persepolis.setTitle("Persepolis");
		persepolis.setYearOfPublication(2005);

		bookManagerImpl.addBook(persepolis);

		assertEquals(bookManagerImpl.getBookById(1).getId(), 1);

	}

	@Test
	public void addBookShouldAddNewBook() {

		Book maus = new Book();
		maus.setId(2);
		maus.setAuthorName("Art");
		maus.setAuthorSurname("Spiegelman");
		maus.setTitle("Maus");
		maus.setYearOfPublication(2009);

		bookManagerImpl.addBook(maus);

		assertEquals(bookManagerImpl.getBookById(2).getTitle(), maus.getTitle());

	}

}
