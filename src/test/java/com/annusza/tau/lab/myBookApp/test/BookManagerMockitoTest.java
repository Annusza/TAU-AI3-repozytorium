package com.annusza.tau.lab.myBookApp.test;

import com.annusza.tau.lab.myBookApp.domain.Book;
import com.annusza.tau.lab.myBookApp.service.BookManagerImpl;

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

	// 3 - test sposobu ustawiania daty przy dodawaniu rekordu
	@Test
	public void mockitoTestOfSettingDateDuringAddBook() throws Exception {

		BookManagerImpl bookManagerImpl = spy(BookManagerImpl.class);

		when((bookManagerImpl).getCurrentDateTime()).thenReturn(mockDate);

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

	}

	// 4 - test sposobu ustawiania daty przy aktualizacji
	@Test
	public void mockitoTestOfUpdatingDateDuringUpdateBook() throws Exception {

		BookManagerImpl bookManagerImpl = spy(BookManagerImpl.class);
		when((bookManagerImpl).getCurrentDateTime()).thenReturn(mockDate);

		Book book = new Book();
		final int id = 2;
		book.setId(id);
		book.setAuthorName("Włodzimierz");
		book.setAuthorSurname("Odojewski");
		book.setTitle("Zasypie wszystko, zawieje");
		book.setYearOfPublication(2000);
		bookManagerImpl.addBook(book);

		// update Book
		book.setTitle("Wyspa ocalenia");
		bookManagerImpl.updateBook(book);

		// weryfikacja 1) czy BookManagerImpl razy jeden zawołał setTimeOfUpdate
		// z argumentem book
		verify(bookManagerImpl, times(1)).setTimeOfUpdate(book);

		// weryfikacja 2) metoda ustawiła updateRowTime
		Assert.assertNotNull("Ustawiona jest data aktualizacji rekordu", book.getUpdateRowTime());

	}

	// 2 - test sposobu ustawiania daty przy odczycie rekordu
	@Test
	public void mockitoTestOfLastReadRowTimeDuringUsingGetBookById() throws Exception {

		BookManagerImpl bookManagerImpl = spy(BookManagerImpl.class);
		when((bookManagerImpl).getCurrentDateTime()).thenReturn(mockDate);

		Book anneOfGreenGables = new Book();
		final int id = 3;
		anneOfGreenGables.setId(id);
		anneOfGreenGables.setAuthorName("Lucy Maud");
		anneOfGreenGables.setAuthorSurname("Montgomery");
		anneOfGreenGables.setTitle("Annie of the Green Gables");
		anneOfGreenGables.setYearOfPublication(1997);
		bookManagerImpl.addBook(anneOfGreenGables);
		
		Book nextBook = bookManagerImpl.getBookById(id);

		verify(bookManagerImpl, times(1)).setTimeOfRead(nextBook);
		
		Assert.assertNotNull("Ustawiona jest data odczytu rekordu", nextBook.getReadRowTime());
	}

}
