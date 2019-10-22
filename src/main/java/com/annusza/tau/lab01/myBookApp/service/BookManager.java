package com.annusza.tau.lab01.myBookApp.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.annusza.tau.lab01.myBookApp.domain.Book;
import com.annusza.tau.lab01.myBookApp.domain.TheTime;

public interface BookManager {

	public void addBook(Book book) throws Exception;

	public Book getBookById(int id) throws Exception;

	public Book updateBook(Book book) throws Exception;

	public void deleteBook(int id) throws Exception;

	public List<Book> getAllBooks() throws Exception;

	public void setTimeOfRead(LocalDateTime mockDate, TheTime theTime);

	public void setTimeOfCreation(Book book);

	public LocalDateTime getActualDate() throws Exception;
}
