package com.capgemini.serviciosya.beans.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "Country")
@Table(name = "country")
public class CountryEntity {
	// Map the fields (Database columns) and properties(Java classes)
	@Id
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "country_seq")
	//@GeneratedValue (strategy = GenerationType.IDENTITY)
	//@Column (name = "id", nullable = false, insertable = false, updatable = false)
	@SequenceGenerator (name = "country_seq", sequenceName= "country_seq")
	@Column(name = "id") // si se llama diferente
	private int id;

	@Column(name = "name", length = 48, nullable = false)
	private String name;

	public CountryEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CountryEntity(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
