package com.annusza.tau.service;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.annusza.tau.domain.Book;
import com.annusza.tau.domain.JMBook;

//@Component

public interface JMBookManager {

	public Connection getConnection();
	public void setConnection(Connection connection) throws SQLException;

	public int addBook(JMBook book);
	public int deleteBook(JMBook book) throws SQLException;
	public int updateBook(JMBook book) throws SQLException;
	public JMBook getBookById(int id) throws SQLException;
	public List<JMBook> getAllBooks();
	public int deleteAll();



}
