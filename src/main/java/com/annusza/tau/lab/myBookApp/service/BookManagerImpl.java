package com.annusza.tau.lab.myBookApp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.annusza.tau.lab.myBookApp.domain.*;

public class BookManagerImpl implements BookManager {

	List<Book> books = new ArrayList<Book>();

	@Override
	public void addBook(Book book) throws Exception {

		setTimeOfCreation(book);
		books.add(book);

	}

	@Override
	public Book getBookById(int id) throws Exception {

		for (Book book : books) {
			if (book.getId() == id) {

				Book book2 = new Book();

				book2.setId(book.getId());
				book2.setAuthorName(book.getAuthorName());
				book2.setAuthorSurname(book.getAuthorSurname());
				book2.setTitle(book.getTitle());
				book2.setYearOfPublication(book.getYearOfPublication());
				book2.setCreateRowTime(book.getCreateRowTime());
				book2.setUpdateRowTime(book.getUpdateRowTime());
				book2.setReadRowTime(book.getReadRowTime());

				return book2;
			}
		}
		return null;
	}

	@Override
	public Book updateBook(Book book) throws Exception {

		Book bookToUpdate = new Book();
		
		if (bookToUpdate != null) {

			bookToUpdate.setAuthorName(book.getAuthorName());
			bookToUpdate.setAuthorSurname(book.getAuthorSurname());
			bookToUpdate.setTitle(book.getTitle());
			bookToUpdate.setId(book.getId());
			bookToUpdate.setYearOfPublication(book.getYearOfPublication());
			bookToUpdate.setTime(book.getTime());
			bookToUpdate.setCreateRowTime(book.getCreateRowTime());
			bookToUpdate.setUpdateRowTime(book.getUpdateRowTime());
			bookToUpdate.setReadRowTime(book.getReadRowTime());
			
			setTimeOfLastUpdate(bookToUpdate);
			
			return bookToUpdate;
		}

		return null;

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

		return books;

	}

	@Override
	public LocalDateTime getActualDate() {

		return LocalDateTime.now();
	}

	@Override
	public void setTimeOfCreation(Book book) throws Exception {

		book.setCreateRowTime(getActualDate());

	}

	@Override
	public void setTimeOfRead(LocalDateTime mockDate, TheTime theTime) {

		// TODO Auto-generated method stub

	}

	@Override
	public void setTimeOfLastUpdate(Book book) throws Exception {

		LocalDateTime updateTime = LocalDateTime.now();
		book.setUpdateRowTime(updateTime);
	}

}
