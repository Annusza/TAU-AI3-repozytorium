package com.annusza.tau.lab01.myBookApp.test;

import com.annusza.tau.lab01.myBookApp.domain.Book;
import com.annusza.tau.lab01.myBookApp.service.BookManagerImpl;

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

		verify(bookManagerImpl, times(1)).setDateTimeOfRead(nextBook);

		Assert.assertNotNull("Ustawiona jest data odczytu rekordu", nextBook.getReadRowDateTime());
	}

	// 2b - test sposobu ustawiania daty przy odczycie rekordu getById
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
		madameBovary.setCreateRowTime(mockDate);
		madameBovary.setUpdateRowTime(mockDate);
		madameBovary.setReadRowTime(mockDate);

		bookManagerImpl.addBook(madameBovary);

		Book bookWithDateTimeFlags = bookManagerImpl.getInformationAboutBookDateTime(madameBovary);
		verify(bookManagerImpl, times(1)).setInformationAboutBookDateTime(bookWithDateTimeFlags);
		Assert.assertNotNull("Ustawione są flagi czasowe: data i czas tworzenia rekordu", bookWithDateTimeFlags.getCreateRowDateTime());
		Assert.assertNotNull("Ustawione są flagi czasowe: data i czas aktualizacji rekordu", bookWithDateTimeFlags.getUpdateRowDateTime());
		Assert.assertNotNull("Ustawione są flagi czasowe: data i czas odczytu rekordu", bookWithDateTimeFlags.getReadRowDateTime());
	}

	// 6 - test metody do włączania/wyłączania zapisywania poszczególnych
	// znaczników czasowych

	// test save on
	@Test
	public void mockitoTestIsTurnOnSaveDateTimeOfCreateWOrking() throws Exception {

		BookManagerImpl bookManagerImpl = spy(BookManagerImpl.class);
		when((bookManagerImpl).getCurrentDateTime()).thenReturn(mockDate);

		Book doll = new Book();
		final int id = 1;
		doll.setId(id);
		doll.setAuthorName("Bolesław");
		doll.setAuthorSurname("Prus");
		doll.setTitle("The doll");
		doll.setYearOfPublication(1956);
		doll.setCreateRowTime(mockDate);
		doll.setUpdateRowTime(mockDate);
		doll.setReadRowTime(mockDate);
		doll.setSaveDateTimeOfCreate(false);
		bookManagerImpl.turnOnSaveDateTimeOfCreate(doll);

		verify(bookManagerImpl, times(0)).turnOffSaveDateTimeOfCreate(doll);
		Assert.assertNotNull("Uruchamiana jest opcja włączenia czasu dodania rektordu do bazy", doll.isSaveDateTimeOfCreate());
	}

	// test save off
	@Test
	public void mockitoTestIsTurnOffSaveDateTimeOfCreateWorking() throws Exception {

		BookManagerImpl bookManagerImpl = spy(BookManagerImpl.class);
		when((bookManagerImpl).getCurrentDateTime()).thenReturn(mockDate);

		Book pharaoh = new Book();
		final int id = 2;
		pharaoh.setId(id);
		pharaoh.setAuthorName("Bolesław");
		pharaoh.setAuthorSurname("Prus");
		pharaoh.setTitle("Pharaoh");
		pharaoh.setYearOfPublication(1977);
		pharaoh.setCreateRowTime(mockDate);
		pharaoh.setUpdateRowTime(mockDate);
		pharaoh.setReadRowTime(mockDate);
		pharaoh.setSaveDateTimeOfCreate(true);
		bookManagerImpl.turnOffSaveDateTimeOfCreate(pharaoh);

		verify(bookManagerImpl, times(0)).turnOnSaveDateTimeOfCreate(pharaoh);
		Assert.assertNotNull("Uruchamiana jest opcja wyłączenia czasu dodania rektordu do bazy", pharaoh.isSaveDateTimeOfCreate());
	}

	// test update on
	@Test
	public void mockitoTestIsTurnOnSaveDateTimeOfUpdateWorking() throws Exception {

		BookManagerImpl bookManagerImpl = spy(BookManagerImpl.class);
		when((bookManagerImpl).getCurrentDateTime()).thenReturn(mockDate);

		Book emancipationists = new Book();
		final int id = 3;
		emancipationists.setId(id);
		emancipationists.setAuthorName("Bolesław");
		emancipationists.setAuthorSurname("Prus");
		emancipationists.setTitle("Emancipacionists");
		emancipationists.setYearOfPublication(1985);
		emancipationists.setCreateRowTime(mockDate);
		emancipationists.setUpdateRowTime(mockDate);
		emancipationists.setReadRowTime(mockDate);
		emancipationists.setSaveDateTimeOfUpdate(false);
		bookManagerImpl.turnOnSaveDateTimeOfUpdate(emancipationists);
		;

		verify(bookManagerImpl, times(0)).turnOffSaveDateTimeOfUpdate(emancipationists);
		Assert.assertNotNull("Uruchamiana jest opcja aktualizacji czasu dodania rektordu do bazy", emancipationists.isSaveDateTimeOfCreate());

	}

	// test update off
	@Test
	public void mockitoTestIsTurnOffSaveDateTimeOfUpdateWorking() throws Exception {

		BookManagerImpl bookManagerImpl = spy(BookManagerImpl.class);
		when((bookManagerImpl).getCurrentDateTime()).thenReturn(mockDate);

		Book children = new Book();
		final int id = 4;
		children.setId(id);
		children.setAuthorName("Bolesław");
		children.setAuthorSurname("Prus");
		children.setTitle("Children");
		children.setYearOfPublication(1960);
		children.setCreateRowTime(mockDate);
		children.setUpdateRowTime(mockDate);
		children.setReadRowTime(mockDate);
		children.setSaveDateTimeOfUpdate(true);
		bookManagerImpl.turnOffSaveDateTimeOfUpdate(children);

		verify(bookManagerImpl, times(0)).turnOnSaveDateTimeOfUpdate(children);
		Assert.assertNotNull("Uruchamiana jest opcja aktualizacji czasu dodania rektordu do bazy", children.isSaveDateTimeOfCreate());

	}

	// test read on
	@Test
	public void mockitoTestIsTurnOnSaveDateTimeOfReadWorking() throws Exception {

		BookManagerImpl bookManagerImpl = spy(BookManagerImpl.class);
		when((bookManagerImpl).getCurrentDateTime()).thenReturn(mockDate);

		Book shadows = new Book();
		final int id = 5;
		shadows.setId(id);
		shadows.setAuthorName("Bolesław");
		shadows.setAuthorSurname("Prus");
		shadows.setTitle("Shadows");
		shadows.setYearOfPublication(1966);
		shadows.setCreateRowTime(mockDate);
		shadows.setUpdateRowTime(mockDate);
		shadows.setReadRowTime(mockDate);
		shadows.setSaveDateTimeOfRead(false);
		bookManagerImpl.turnOnSaveDateTimeOfRead(shadows);
		;

		verify(bookManagerImpl, times(0)).turnOffSaveDateTimeOfRead(shadows);
		Assert.assertNotNull("Uruchamiana jest opcja aktualizacji czasu dodania rektordu do bazy", shadows.isSaveDateTimeOfRead());

	}

	// test read off
	@Test
	public void mockitoTestIsTurnOffSaveDateTimeOfReadWorking() throws Exception {

		BookManagerImpl bookManagerImpl = spy(BookManagerImpl.class);
		when((bookManagerImpl).getCurrentDateTime()).thenReturn(mockDate);

		Book mistake = new Book();
		final int id = 6;
		mistake.setId(id);
		mistake.setAuthorName("Bolesław");
		mistake.setAuthorSurname("Prus");
		mistake.setTitle("Mistake");
		mistake.setYearOfPublication(1991);
		mistake.setCreateRowTime(mockDate);
		mistake.setUpdateRowTime(mockDate);
		mistake.setReadRowTime(mockDate);
		mistake.setSaveDateTimeOfRead(true);
		bookManagerImpl.turnOffSaveDateTimeOfRead(mistake);

		verify(bookManagerImpl, times(0)).turnOnSaveDateTimeOfRead(mistake);
		Assert.assertNotNull("Uruchamiana jest opcja aktualizacji czasu dodania rektordu do bazy", mistake.isSaveDateTimeOfRead());

	}

}
