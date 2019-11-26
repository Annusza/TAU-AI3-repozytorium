package com.annusza.tau.domain;


public class JMBook {
	
	private int id;
	private String authorName;
	private String authorSurname;
	private String title;
	private int yearOfPublication;
	//private DateTime dateTime;

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

//	public DateTime getTime() {
//
//		return dateTime;
//	}
//
//	public void setTime(DateTime time) {
//
//		this.dateTime = time;
//	}
	
//	 @Override
//	    public int hashCode() {
//	        return Objects.hash(id);
//	    }

//	@Override
//	public boolean equals(Object object) {
//
//		Book other = (Book) object;
//		boolean deliver = other.getAuthorName().equals(this.getAuthorName()) && other.getAuthorSurname().equals(this.getAuthorSurname())
//				&& other.getTitle().equals(this.getTitle()) && ((other.getId() == this.getId()) || (other.getId() == this.getId().intValue()))
//				&& ((other.getYearOfPublication() == this.getYearOfPublication())
//						|| (other.getYearOfPublication().intValue() == this.getYearOfPublication().intValue()));
//		return deliver;
//	}
	
	//???
	
	public JMBook() {
		
	}
	
	public JMBook(int id, String authorName, String authorSurname, String title, int yearOfPublication)
	{
		this.id = id;
		this.authorName = authorName;
		this.authorSurname = authorSurname;
		this.title = title;
		this.title = title;
		this.yearOfPublication = yearOfPublication;
		//this.dateTime = dateTime;
	
	}


}
