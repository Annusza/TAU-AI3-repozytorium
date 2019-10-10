package com.annusza.tau.lab01.myBookApp.service;

import java.util.List;

import com.annusza.tau.lab01.myBookApp.domain.Book;

public interface BookManager {
	
	public void addBook(Book book) throws Exception;
	public Book getBookById(int id) throws Exception;
	public void updateBook(Book book) throws Exception;
	public void deleteBook(int id) throws Exception;
	public List<Book> getAllBooks() throws Exception;

}
