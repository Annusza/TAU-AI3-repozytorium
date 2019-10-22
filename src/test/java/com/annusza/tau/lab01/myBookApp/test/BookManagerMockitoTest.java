package com.annusza.tau.lab01.myBookApp.test;

import com.annusza.tau.lab01.myBookApp.domain.Book;
import com.annusza.tau.lab01.myBookApp.service.BookManagerImpl;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.mockito.AdditionalMatchers.gt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class BookManagerMockitoTest {

	LocalDateTime mockDate = LocalDateTime.of(2016, 11, 18, 18, 15);

	@Before
	public void setup() {

		assertNotNull(mockDate);

	}

	// 3 - test ustawiania daty przy dodawaniu rekordu
	@Test
	public void mockitoTestOfSettingDateDuringAddBook() throws Exception {

		BookManagerImpl bookManagerImpl = spy(BookManagerImpl.class);

		when((bookManagerImpl).getActualDate()).thenReturn(mockDate);

		Book book1 = new Book();
		final int id = 1;
		book1.setId(id);
		book1.setAuthorName("Eric-Emmanuel");
		book1.setAuthorSurname("Schmitt");
		book1.setTitle("Oscar and the Lady in Pink");
		book1.setYearOfPublication(2005);
		bookManagerImpl.addBook(book1);

		Book book2 = bookManagerImpl.getBookById(id);
		verify(bookManagerImpl, times(1)).setTimeOfCreation(book1);
		Assert.assertNotNull("Zapisywany jest czas dodania rektordu do bazy", book2.getCreateRowTime());
		// (mockDate, book2);

	}

	@Test
	public void mockitoTestGetBook() throws Exception {

		BookManagerImpl bookManagerImpl = spy(BookManagerImpl.class);
		when((bookManagerImpl).getActualDate()).thenReturn(mockDate);

		Book book1 = new Book();
		final int id = 1;
		book1.setId(id);
		book1.setAuthorName("Eric-Emmanuel");
		book1.setAuthorSurname("Schmitt");
		book1.setTitle("Oscar and the Lady in Pink");
		book1.setYearOfPublication(2005);

		bookManagerImpl.addBook(book1);

		Book book2 = bookManagerImpl.getBookById(id);
		// Assert.assertNotNull("Data odczytu pozycji",
		// book2.getTimeOfLastReadTheRow());
		// verify(bookManagerImpl, times(1)).setTimeOfRead(mockDate, book2);

	}

}
