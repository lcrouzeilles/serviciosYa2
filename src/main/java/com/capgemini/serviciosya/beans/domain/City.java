package com.capgemini.serviciosya.beans.domain;

public class City {
	private int id;
	private String name;
	public City(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public City() {

	}
	public int getId_city() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
