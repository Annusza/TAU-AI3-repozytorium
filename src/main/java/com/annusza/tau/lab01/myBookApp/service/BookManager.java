package com.annusza.tau.lab01.myBookApp.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.annusza.tau.lab01.myBookApp.domain.Book;
import com.annusza.tau.lab01.myBookApp.domain.DateTime;

public interface BookManager {

	// CRUD
	public void addBook(Book book) throws Exception;

	public Book getBookById(int id) throws Exception;

	public void updateBook(Book book) throws Exception;

	public void deleteBook(int id) throws Exception;

	public List<Book> getAllBooks() throws Exception;

	//

	public void setDateTimeOfRead(Book book) throws Exception;

	public void setDateTimeOfCreation(Book book) throws Exception;

	public LocalDateTime getCurrentDateTime() throws Exception;

	public void setDateTimeOfUpdate(Book book) throws Exception;

	public Book getInformationAboutBookDateTime(Book book) throws Exception;

	public void setInformationAboutBookDateTime(Book book) throws Exception;

	// TIME

	public void turnOnSaveDateTimeOfRead(Book book) throws Exception;

	public void turnOffSaveDateTimeOfRead(Book book) throws Exception;

	public void turnOnSaveDateTimeOfCreate(Book book) throws Exception;

	public void turnOffSaveDateTimeOfCreate(Book book) throws Exception;

	public void turnOnSaveDateTimeOfUpdate(Book book) throws Exception;

	public void turnOffSaveDateTimeOfUpdate(Book book) throws Exception;
}
