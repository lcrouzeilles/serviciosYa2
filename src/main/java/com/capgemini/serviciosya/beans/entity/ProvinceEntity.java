package com.capgemini.serviciosya.beans.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "Province")
@Table(name = "province")
public class ProvinceEntity {

	// Map the fields (Database columns) and properties(Java classes)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "province_seq")
	// @GeneratedValue (strategy = GenerationType.IDENTITY)
	// @Column (name = "id", nullable = false, insertable = false, updatable =
	// false)
	@SequenceGenerator(name = "province_seq", sequenceName = "province_seq")
	@Column(name = "id", nullable = false, insertable = false, updatable = false)
	private int id;

	@Column(name = "name", length = 48, nullable = false)
	private String name;	
	
	@ManyToOne
	@JoinColumn(name = "country_id")
	private CountryEntity country;
	
	public ProvinceEntity() {

	}

	public ProvinceEntity(int id, String name, CountryEntity country) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
	}
	public CountryEntity getCountry() {
		return country;
	}

	public void setCountry(CountryEntity country) {
		this.country = country;
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
