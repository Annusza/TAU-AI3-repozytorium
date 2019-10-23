package com.annusza.tau.lab.myBookApp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.annusza.tau.lab.myBookApp.domain.*;

public class BookManagerImpl implements BookManager {

	List<Book> books = new ArrayList<Book>();

	@Override
	public void addBook(Book book) throws Exception {

		setDateTimeOfCreation(book);
		books.add(book);

	}

	@Override
	public Book getBookById(int id) throws Exception {

		for (Book book : books) {
			if (book.getId() == id) {

				Book book2 = clone(book);
				setDateTimeOfRead(book2);
				return book2;
			}
		}
		return null;
	}

	@Override
	public void setDateTimeOfRead(Book book) throws Exception {

		book.setReadRowTime(getCurrentDateTime());

	}

	private Book clone(Book book) throws Exception {

		Book book2 = new Book();

		book2.setId(book.getId());
		book2.setAuthorName(book.getAuthorName());
		book2.setAuthorSurname(book.getAuthorSurname());
		book2.setTitle(book.getTitle());
		book2.setYearOfPublication(book.getYearOfPublication());
		book2.setCreateRowTime(book.getCreateRowDateTime());
		book2.setUpdateRowTime(book.getUpdateRowDateTime());
		book2.setReadRowTime(book.getReadRowDateTime());

		// setTimeOfRead(book2);
		return book2;
	}

	@Override
	public void deleteBook(int id) throws Exception {

		Book book = getBookById(id);
		if (book != null) {
			books.remove(book);
		}

	}

	@Override
	public List<Book> getAllBooks() throws Exception {

		List<Book> listOfReturnedBooks = new ArrayList<Book>();

		for (Book book : books) {
			Book cloneBook = clone(book);
			setDateTimeOfRead(cloneBook);
			listOfReturnedBooks.add(cloneBook);

		}
		return listOfReturnedBooks;

	}

	@Override
	public void updateBook(Book book) throws Exception {

		Book bookToUpdate = null;

		for (Book bookOfList : books) {
			if (bookOfList.getId().equals(book.getId())) {

				bookToUpdate = bookOfList;

				break;
			}
		}

		if (bookToUpdate != null) {

			bookToUpdate.setAuthorName(book.getAuthorName());
			bookToUpdate.setAuthorSurname(book.getAuthorSurname());
			bookToUpdate.setTitle(book.getTitle());
			bookToUpdate.setId(book.getId());
			bookToUpdate.setYearOfPublication(book.getYearOfPublication());
			bookToUpdate.setTime(book.getTime());
			bookToUpdate.setCreateRowTime(book.getCreateRowDateTime());
			bookToUpdate.setUpdateRowTime(book.getUpdateRowDateTime());
			bookToUpdate.setReadRowTime(book.getReadRowDateTime());

			setDateTimeOfUpdate(bookToUpdate);

		} else {

			throw new Exception("Nie ma wskazanej książki w bazie");
		}

	}

	@Override
	public void setDateTimeOfUpdate(Book book) {

		book.setUpdateRowTime(getCurrentDateTime());

	}

	@Override
	public LocalDateTime getCurrentDateTime() {

		return LocalDateTime.now();
	}

	@Override
	public void setDateTimeOfCreation(Book book) throws Exception {

		book.setCreateRowTime(getCurrentDateTime());

	}

}
