package com.annusza.tau.lab01.myBookApp.domain;

import java.time.LocalDateTime;

public class Book extends TheTime {

	private Integer id;
	private String authorName;
	private String authorSurname;
	private String title;
	private Integer yearOfPublication;

	private TheTime time;

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

	public Integer getYearOfPublication() {

		return yearOfPublication;
	}

	public void setYearOfPublication(Integer yearOfPublication) {

		this.yearOfPublication = yearOfPublication;
	}

	public void setId(Integer id) {

		this.id = id;
	}

	public Integer getId() {

		return id;
	}

	public TheTime getTime() {

		return time;
	}

	public void setTime(TheTime time) {

		this.time = time;
	}

	@Override
	public boolean equals(Object object) {

		Book other = (Book) object;
		boolean deliver = other.getAuthorName().equals(this.getAuthorName()) && other.getAuthorSurname().equals(this.getAuthorSurname())
				&& other.getTitle().equals(this.getTitle()) && ((other.getId() == this.getId()) || (other.getId() == this.getId().intValue()))
				&& ((other.getYearOfPublication() == this.getYearOfPublication())
						|| (other.getYearOfPublication().intValue() == this.getYearOfPublication().intValue()));
		return deliver;
	}

}
