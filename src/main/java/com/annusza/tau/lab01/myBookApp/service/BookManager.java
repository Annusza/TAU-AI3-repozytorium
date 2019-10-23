package com.annusza.tau.lab01.myBookApp.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.annusza.tau.lab01.myBookApp.domain.Book;
import com.annusza.tau.lab01.myBookApp.domain.TheTime;

public interface BookManager {

	public void addBook(Book book) throws Exception;

	public Book getBookById(int id) throws Exception;

	public void updateBook(Book book) throws Exception;

	public void deleteBook(int id) throws Exception;

	public List<Book> getAllBooks() throws Exception;

	public void setDateTimeOfRead(Book book) throws Exception;

	public void setDateTimeOfCreation(Book book) throws Exception;

	public LocalDateTime getCurrentDateTime() throws Exception;

	public void setDateTimeOfUpdate(Book book) throws Exception;
}
