package br.edu.ifpb.bd2.hbase.entities;

import java.util.Date;

public class Edition {

	private String name;
	private Integer year;
	private Date release;

	public Edition() {
	}

	public Edition(String name, Integer year, Date release) {
		this.name = name;
		this.year = year;
		this.release = release;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Date getRelease() {
		return release;
	}

	public void setRelease(Date release) {
		this.release = release;
	}

	@Override
	public String toString() {
		return "Edition [name=" + name + ", year=" + year + ", release="
				+ release + "]";
	}

}
