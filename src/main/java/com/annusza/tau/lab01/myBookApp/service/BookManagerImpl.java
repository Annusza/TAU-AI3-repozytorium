package com.annusza.tau.lab01.myBookApp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.annusza.tau.lab01.myBookApp.domain.*;

public class BookManagerImpl implements BookManager {

	List<Book> books = new ArrayList<Book>();

	public boolean saveDateTimeOfCreate = true;
	public boolean saveDateTimeOfUpdate = true;
	public boolean saveDateTimeOfRead = true;

	@Override
	public void addBook(Book book) throws Exception {

		if (isSaveDateTimeOfCreate()) {

			setDateTimeOfCreation(book);

		}
		books.add(book);

	}

	@Override
	public Book getBookById(int id) throws Exception {

		for (Book book : books) {
			if (book.getId() == id) {

				Book book2 = clone(book);

				if (isSaveDateTimeOfRead()) {

					setDateTimeOfRead(book2);
				}

				return book2;
			}
		}
		return null;
	}

	private Book clone(Book book) throws Exception {

		Book cloneOfBook = new Book();

		cloneOfBook.setId(book.getId());
		cloneOfBook.setAuthorName(book.getAuthorName());
		cloneOfBook.setAuthorSurname(book.getAuthorSurname());
		cloneOfBook.setTitle(book.getTitle());
		cloneOfBook.setYearOfPublication(book.getYearOfPublication());
		cloneOfBook.setCreateRowTime(book.getCreateRowDateTime());
		cloneOfBook.setUpdateRowTime(book.getUpdateRowDateTime());

		return cloneOfBook;
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

			if (isSaveDateTimeOfUpdate()) {

				setDateTimeOfUpdate(bookToUpdate);

			}

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

	@Override
	public void setDateTimeOfRead(Book book) throws Exception {

		book.setReadRowTime(getCurrentDateTime());

	}

	@Override
	public Book getInformationAboutBookDateTime(Book book) throws Exception {

		Book bookWithInformationAboutDateTime = book;
		setInformationAboutBookDateTime(bookWithInformationAboutDateTime);
		return bookWithInformationAboutDateTime;

	}

	@Override
	public void setInformationAboutBookDateTime(Book book) throws Exception {

		setDateTimeOfCreation(book);
		setDateTimeOfUpdate(book);
		setDateTimeOfRead(book);

	}

	@Override
	public boolean isSaveDateTimeOfCreate() {

		return saveDateTimeOfCreate;
	}

	@Override
	public void setSaveDateTimeOfCreate(boolean saveDateTimeOfCreate) {

		this.saveDateTimeOfCreate = saveDateTimeOfCreate;
	}

	@Override
	public boolean isSaveDateTimeOfUpdate() {

		return saveDateTimeOfUpdate;
	}

	@Override
	public void setSaveDateTimeOfUpdate(boolean saveDateTimeOfUpdate) {

		this.saveDateTimeOfUpdate = saveDateTimeOfUpdate;
	}

	@Override
	public boolean isSaveDateTimeOfRead() {

		return saveDateTimeOfRead;
	}

	@Override
	public void setSaveDateTimeOfRead(boolean saveDateTimeOfRead) {

		this.saveDateTimeOfRead = saveDateTimeOfRead;
	}
	


}
