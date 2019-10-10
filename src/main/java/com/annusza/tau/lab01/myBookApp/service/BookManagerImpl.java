package com.annusza.tau.lab01.myBookApp.service;

import java.util.ArrayList;
import java.util.List;

import com.annusza.tau.lab01.myBookApp.domain.Book;

public class BookManagerImpl implements BookManager {

	List<Book> books = new ArrayList<Book>();

	public void addBook(Book book) throws Exception {

		books.add(book);

	}

	public Book getBookById(int id) throws Exception {

		for (Book book : books) {
			if (book.getId() == id) {

				return book;
			}
		}
		return null;
	}

	public void updateBook(Book book) throws Exception {

		// TODO Auto-generated method stub

	}

	public void deleteBook(int id) throws Exception {

		Book book = getBookById(id);
		if (book != null) {
			books.remove(book);
		}

	}

	public List<Book> getAllBooks() throws Exception {

		// TODO Auto-generated method stub
		return null;
	}

}
