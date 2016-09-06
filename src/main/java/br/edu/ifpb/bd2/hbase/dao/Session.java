package br.edu.ifpb.bd2.hbase.dao;

public class Session {

	private String name;
	private String localization;

	public Session() {
	}

	public Session(String name, String localization) {
		this.name = name;
		this.localization = localization;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocalization() {
		return localization;
	}

	public void setLocalization(String localization) {
		this.localization = localization;
	}

	@Override
	public String toString() {
		return "Session [name=" + name + ", localization=" + localization + "]";
	}
}
