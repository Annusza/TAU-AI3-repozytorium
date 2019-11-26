package com.annusza.tau.web;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.annusza.tau.domain.JMBook;
import com.annusza.tau.service.JMBookManager;


@RestController
public class BookApi
{
	@Autowired
	JMBookManager jmBookManager;
	
	@RequestMapping("/")
	public String index()
	{
		return "Just checking if everything works.";
	}
	
	@RequestMapping(value="/book/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JMBook getBookById(@PathVariable("id") int id) throws SQLException
	{
		return jmBookManager.getBookById(id);
	}
	
	@RequestMapping(value="/books", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<JMBook> getAllBooks(@RequestParam(value="filter", required=false) String filter) throws SQLException
	{
		List<JMBook> books = new LinkedList<JMBook>();
		for(JMBook jmBook : jmBookManager.getAllBooks())
		{
			if(filter == null)
			{
				books.add(jmBook);
			}
			else if(jmBook.getTitle().contains(filter))
			{
				books.add(jmBook);
			}
		}
		return books;
	}
	
	@RequestMapping(value="/book", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public JMBook addBook(@RequestBody JMBook jmBook)
	{
		if(jmBookManager.addBook(jmBook) < 1)
		{
			return null;
		}
		return jmBook;
	}
	
	@RequestMapping(value="/book/{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public int deleteBook(@PathVariable("id") int id) throws SQLException
	{
		return new Integer(jmBookManager.deleteBook(jmBookManager.getBookById(id)));
	}
	
	@RequestMapping(value="/book/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	public int updateBook(@RequestBody JMBook jmBook) throws SQLException
	{
		return jmBookManager.updateBook(jmBook);
	}

	@RequestMapping(value="/book")
	public int deleteAllBooks()
	{
		return jmBookManager.deleteAll();
	}
}