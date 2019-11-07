package com.annusza.tau.lab01.myBookApp.cucumberTest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import com.annusza.tau.lab01.myBookApp.domain.Book;
import com.annusza.tau.lab01.myBookApp.service.BookManagerImpl;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

public class DeleteBooksStepDefs {

	private BookManagerImpl bookManagerImpl = new BookManagerImpl();
	private List<Book> foundBooks;

	// |0 |1 |2
	// |title |year |id|
	@Given("^list of books$")
	public void list_of_books(DataTable dataTable) throws Exception {

		dataTable.asLists().stream().forEach(row -> {
			Book book = new Book();
			book.setId(Integer.parseInt(row.get(2)));
			book.setTitle(row.get(0));
			book.setYearOfPublication(Integer.parseInt(row.get(1)));
			try {
				bookManagerImpl.addBook(book);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	@When("^we delete books by list of years (.*)$")
	public void delete_books_by_list_of_years(String yearsStr) throws Exception {

		List<Integer> yearsInt = new ArrayList<>();
		String[] split = yearsStr.replace(" ", "").split("[^0-9]");
		for (String str : split) {
			yearsInt.add(Integer.parseInt(str));
		}

		bookManagerImpl.deleteByYears(yearsInt);

		foundBooks = bookManagerImpl.getAllBooks();
	}

	@Then("^book with title (.*) should be deleted$")
	public void book_with_title_should_be_deleted(String title) throws Exception {

		boolean actual = foundBooks.stream().noneMatch(b -> b.getTitle().equals(title));
		assertTrue(title, actual);

	}
}
