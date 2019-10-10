package com.annusza.tau.lab01.myBookApp.service;

import java.util.List;

import com.annusza.tau.lab01.myBookApp.domain.Book;

public interface BookManager {
	
	public void addBook(Book book);
	public Book getBookById(int id);
	public void updateBook(Book book);
	public void deleteBook(int id);
	public List<Book> getAllGames();

}
