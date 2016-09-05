package br.edu.ifpb.bd2.hbase.dao;

public class ComicBook {

	private String name;
	private String isbn;

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

	@Override
	public String toString() {
		return "Book [name=" + name + ", isbn=" + isbn + "]";
	}
}
