package com.annusza.tau.lab01.myBookApp.service;

import java.util.ArrayList;
import java.util.List;

import com.annusza.tau.lab01.myBookApp.domain.Book;

public class BookManagerImpl implements BookManager {

	List<Book> books = new ArrayList<Book>();

	public void addBook(Book book) {

		books.add(book);

	}

	public Book getBookById(int id) {

		for (Book book : books) {
			if (book.getId() == id) {

				return book;
			}
		}
		return null;
	}

	public void updateBook(Book book) {

		// TODO Auto-generated method stub

	}

	public void deleteBook(int id) {

		// TODO Auto-generated method stub

	}

	public List<Book> getAllGames() {

		// TODO Auto-generated method stub
		return null;
	}

}
