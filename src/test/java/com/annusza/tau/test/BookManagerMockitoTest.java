package com.annusza.tau.test;

import com.annusza.tau.domain.Book;
import com.annusza.tau.domain.DateTime;
import com.annusza.tau.service.BookManagerImpl;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

	// 6 - testy włączania/wyłączania zapisywania poszczególnych znaczników
	// czasowych

	// test save on/off
	@Test
	public void mockitoTestIsTurnOnOffSaveDateTimeOfCreateWOrking() throws Exception {

		boolean[] array = { true, false };
		for (int i = 0; i < array.length; i++) {

			BookManagerImpl bookManagerImpl = spy(BookManagerImpl.class);
			when((bookManagerImpl).getCurrentDateTime()).thenReturn(mockDate);

			boolean saveDateTimeOfCreate = array[i];
			bookManagerImpl.setSaveDateTimeOfCreate(saveDateTimeOfCreate);

			Book doll = new Book();
			final int id = i + 1;
			doll.setId(id);
			doll.setAuthorName("Bolesław");
			doll.setAuthorSurname("Prus");
			doll.setTitle("The doll");
			doll.setYearOfPublication(1956);
			bookManagerImpl.addBook(doll);

			int howManyTimes = saveDateTimeOfCreate ? 1 : 0;
			verify(bookManagerImpl, times(1)).isSaveDateTimeOfCreate();
			verify(bookManagerImpl, atLeastOnce()).isSaveDateTimeOfCreate();
			verify(bookManagerImpl, times(howManyTimes)).setDateTimeOfCreation(doll);

			if (saveDateTimeOfCreate) {

				Assert.assertNotNull("Przy włączonym zapisywaniu znacznika czasowego dodania znacznik jest zapisywany", doll.getCreateRowDateTime());
			} else {

				Assert.assertNull("Przy wyłączonym zapisywaniu znacznika czasowego dodania znacznik nie jest zapisywany", doll.getCreateRowDateTime());
			}

			LocalDateTime expected = saveDateTimeOfCreate ? mockDate : null;
			LocalDateTime actual = doll.getCreateRowDateTime();
			assertEquals("Znacznik czasowy dodania jest zapisywany zgodnie z ustawieniem", expected, actual);
		}

	}

	// test update on/off
	@Test
	public void mockitoTestIsTurnOnOffSaveDateTimeOfUpdateWorking() throws Exception {

		boolean[] array = { true, false };
		for (int i = 0; i < array.length; i++) {

			BookManagerImpl bookManagerImpl = spy(BookManagerImpl.class);
			when((bookManagerImpl).getCurrentDateTime()).thenReturn(mockDate);

			boolean saveDateTimeOfUpdate = array[i];
			bookManagerImpl.setSaveDateTimeOfUpdate(saveDateTimeOfUpdate);

			Book pharaoh = new Book();
			final int id = i + 1;
			pharaoh.setId(id);
			pharaoh.setAuthorName("Bolesław");
			pharaoh.setAuthorSurname("Prus");
			pharaoh.setTitle("The Pharaoh");
			pharaoh.setYearOfPublication(1978);
			bookManagerImpl.addBook(pharaoh);

			pharaoh.setYearOfPublication(1989);
			bookManagerImpl.updateBook(pharaoh);

			int howManyTimes = saveDateTimeOfUpdate ? 1 : 0;
			verify(bookManagerImpl, times(1)).isSaveDateTimeOfUpdate();
			verify(bookManagerImpl, times(howManyTimes)).setDateTimeOfUpdate(pharaoh);

			if (saveDateTimeOfUpdate) {

				Assert.assertNotNull("Przy włączonym zapisywaniu znacznika czasowego aktualizacji znacznik jest zapisywany", pharaoh.getUpdateRowDateTime());
			} else {

				Assert.assertNull("Przy wyłączonym zapisywaniu znacznika czasowego aktualizacji znacznik nie jest zapisywany", pharaoh.getUpdateRowDateTime());
			}

			LocalDateTime expected = saveDateTimeOfUpdate ? mockDate : null;
			LocalDateTime actual = pharaoh.getUpdateRowDateTime();
			assertEquals("Znacznik czasowy dodania jest zapisywany zgodnie z ustawieniem", expected, actual);

		}

	}

	// test read on/off
	@Test
	public void mockitoTestIsTurnOnOffSaveDateTimeOfReadWorking() throws Exception {

		boolean[] array = { true, false };
		for (int i = 0; i < array.length; i++) {

			BookManagerImpl bookManagerImpl = spy(BookManagerImpl.class);
			when((bookManagerImpl).getCurrentDateTime()).thenReturn(mockDate);

			boolean saveDateTimeOfRead = array[i];
			bookManagerImpl.setSaveDateTimeOfRead(saveDateTimeOfRead);

			Book emancipationists = new Book();
			final int id = i + 1;
			emancipationists.setId(id);
			emancipationists.setAuthorName("Bolesław");
			emancipationists.setAuthorSurname("Prus");
			emancipationists.setTitle("Emancipationist");
			emancipationists.setYearOfPublication(1986);
			bookManagerImpl.addBook(emancipationists);

			Book anotherPrusBook = bookManagerImpl.getBookById(id);

			int howManyTimes = saveDateTimeOfRead ? 1 : 0;
			verify(bookManagerImpl, times(1)).isSaveDateTimeOfRead();
			verify(bookManagerImpl, atLeast(1)).isSaveDateTimeOfRead();
			verify(bookManagerImpl, times(howManyTimes)).setDateTimeOfRead(anotherPrusBook);

			if (saveDateTimeOfRead) {

				Assert.assertNotNull("Przy włączonym zapisywaniu znacznika czasowego odczytania znacznik jest zapisywany",
						anotherPrusBook.getReadRowDateTime());
			} else {

				Assert.assertNull("Przy wyłączonym zapisywaniu znacznika czasowego odczytania znacznik nie jest zapisywany",
						anotherPrusBook.getReadRowDateTime());
			}

			LocalDateTime expected = saveDateTimeOfRead ? mockDate : null;
			LocalDateTime actual = anotherPrusBook.getReadRowDateTime();
			assertEquals("Znacznik czasowy dodania jest zapisywany zgodnie z ustawieniem", expected, actual);

		}

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
		verify(bookManagerImpl, times(1)).setDateTimeOfCreation(book1);
		verify(bookManagerImpl, atMost(1)).setDateTimeOfCreation(book1);
		Assert.assertNotNull("Zapisywany jest czas dodania rektordu do bazy", book2.getCreateRowDateTime());

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
		verify(bookManagerImpl, times(1)).setDateTimeOfUpdate(book);

		// weryfikacja 2) metoda ustawiła updateRowTime
		Assert.assertNotNull("Ustawiona jest data aktualizacji rekordu", book.getUpdateRowDateTime());

	}

	// 2a - test sposobu ustawiania daty przy odczycie rekordu getById
	@Test
	public void mockitoTestOfLastReadRowDateTimeDuringUsingGetBookById() throws Exception {

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

		verify(bookManagerImpl, atMost(1)).setDateTimeOfRead(nextBook);

		Assert.assertNotNull("Ustawiona jest data odczytu rekordu", nextBook.getReadRowDateTime());
	}

	// 2b - test sposobu ustawiania daty przy odczycie rekordu getAll
	@Test
	public void mockitoTestOfLastReadRowDateTimeDuringUsingGetAll() throws Exception {

		BookManagerImpl bookManagerImpl = spy(BookManagerImpl.class);
		when((bookManagerImpl).getCurrentDateTime()).thenReturn(mockDate);

		Book anneOfAvonlea = new Book();
		final int id4 = 4;
		anneOfAvonlea.setId(id4);
		anneOfAvonlea.setAuthorName("Lucy Maud");
		anneOfAvonlea.setAuthorSurname("Montgomery");
		anneOfAvonlea.setTitle("Annie of Avonlea");
		anneOfAvonlea.setYearOfPublication(1990);
		bookManagerImpl.addBook(anneOfAvonlea);

		Book magicForMarigold = new Book();
		final int id5 = 5;
		magicForMarigold.setId(id5);
		magicForMarigold.setAuthorName("Lucy Maud");
		magicForMarigold.setAuthorSurname("Montgomery");
		magicForMarigold.setTitle("Magic for Marigold");
		magicForMarigold.setYearOfPublication(1989);
		bookManagerImpl.addBook(magicForMarigold);

		Book theBlythesAreQuoted = new Book();
		final int id6 = 6;
		theBlythesAreQuoted.setId(id6);
		theBlythesAreQuoted.setAuthorName("Lucy Maud");
		theBlythesAreQuoted.setAuthorSurname("Montgomery");
		theBlythesAreQuoted.setTitle("The Blythes are quoted");
		theBlythesAreQuoted.setYearOfPublication(2009);
		bookManagerImpl.addBook(theBlythesAreQuoted);

		List<Book> booksOfLucyMaudMontgomety = bookManagerImpl.getAllBooks();

		for (Book book : booksOfLucyMaudMontgomety) {
			verify(bookManagerImpl, times(1)).setDateTimeOfRead(book);
			Assert.assertNotNull("Ustawiona jest data odczytu rekordu", book.getReadRowDateTime());

		}
	}

	// 5 - test metody pozwalającej na uzyskanie informacji o poszczególnych
	// znacznikach czasowych
	@Test
	public void mockitoTestGetDateTimeFlagsShouldReturnInformationAboutDateTime() throws Exception {

		BookManagerImpl bookManagerImpl = spy(BookManagerImpl.class);
		when((bookManagerImpl).getCurrentDateTime()).thenReturn(mockDate);

		Book madameBovary = new Book();
		final int id = 7;
		madameBovary.setId(id);
		madameBovary.setAuthorName("Gustave");
		madameBovary.setAuthorSurname("Flaubert");
		madameBovary.setTitle("Madame Bovary");
		madameBovary.setYearOfPublication(1947);
		//
		bookManagerImpl.addBook(madameBovary);

		Book verifyBook = bookManagerImpl.getBookById(id);
		verifyBook.setTitle("Salammbo");
		bookManagerImpl.updateBook(verifyBook);

		Book bookWithDateTimeFlags = bookManagerImpl.getBookById(id);

		Assert.assertNotNull("Ustawione są flagi czasowe: data i czas tworzenia rekordu", bookWithDateTimeFlags.getCreateRowDateTime());
		Assert.assertNotNull("Ustawione są flagi czasowe: data i czas aktualizacji rekordu", bookWithDateTimeFlags.getUpdateRowDateTime());
		Assert.assertNotNull("Ustawione są flagi czasowe: data i czas odczytu rekordu", bookWithDateTimeFlags.getReadRowDateTime());
	}
	
	// verify only()
	@Test
	public void mockitoTestShoulVerifyOnlyMethodCall() throws Exception {
		
		BookManagerImpl bookManagerImpl = spy(BookManagerImpl.class);
		when((bookManagerImpl).getCurrentDateTime()).thenReturn(mockDate);
		
		
		Book mockBook = mock(Book.class);
		mockBook.setAuthorName("Jan");
		verify(mockBook, only()).setAuthorName("Jan");
	}

}
