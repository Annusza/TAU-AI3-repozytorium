package com.annusza.tau.lab01.myBookApp.cucumberTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;

import com.annusza.tau.lab01.myBookApp.domain.Book;
import com.annusza.tau.lab01.myBookApp.service.BookManagerImpl;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

public class BookStepDefs {

	private BookManagerImpl bookManagerImpl = new BookManagerImpl();
	private List<Book> foundBooks;

	// |0 |1 |2 |3|4|
	// |name |surname |title |id|yearOfPublication|
	@Given("^the list of books$")
	public void list_of_books(DataTable dataTable) throws Exception {

		dataTable.asLists().stream().forEach(row -> {
			Book book = new Book();
			book.setId(Integer.parseInt(row.get(3)));
			book.setAuthorName(row.get(0));
			book.setAuthorSurname(row.get(1));
			book.setTitle(row.get(2));
			book.setYearOfPublication(Integer.parseInt(row.get(4)));
			try {
				bookManagerImpl.addBook(book);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@When("^we find books starting with surname (.*)$")
	public void we_find_books_starting_with_surname_regex(String regex) throws Exception {

		foundBooks = bookManagerImpl.findBooksByAuthorSurname(regex);
	}

	@Then("^we have a result list (.*)$")
	public void we_have_a_titles_result_list(String title) throws Exception {

		boolean actual = foundBooks.stream().anyMatch(b -> b.getTitle().equals(title));
		assertTrue(title, actual);

	}
}
