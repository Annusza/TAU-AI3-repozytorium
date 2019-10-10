package com.annusza.tau.lab01.myBookApp.domain;


public class Book {

	protected Long id;
	private String authorName;
	private String authorSurname;
	private String title;
	private int yearOfPublication;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public String getAuthorName() {

		return authorName;
	}

	public void setAuthorName(String authorName) {

		this.authorName = authorName;
	}

	public String getAuthorSurname() {

		return authorSurname;
	}

	public void setAuthorSurname(String authorSurname) {

		this.authorSurname = authorSurname;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public int getYearOfPublication() {

		return yearOfPublication;
	}

	public void setYearOfPublication(int yearOfPublication) {

		this.yearOfPublication = yearOfPublication;
	}

}
