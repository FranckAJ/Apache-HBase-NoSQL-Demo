package br.edu.ifpb.bd2.hbase.entities;

public class ComicBook {

	private String name;
	private String isbn;
	private Integer numberOfPages;
	private Session session;
	private Edition edition;

	public ComicBook() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Integer getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public Edition getEdition() {
		return edition;
	}

	public void setEdition(Edition edition) {
		this.edition = edition;
	}

	@Override
	public String toString() {
		return "ComicBook [name=" + name + ", isbn=" + isbn + ", session="
				+ session + ", numberOfPages=" + numberOfPages + ", edition="
				+ edition + "]";
	}
}
