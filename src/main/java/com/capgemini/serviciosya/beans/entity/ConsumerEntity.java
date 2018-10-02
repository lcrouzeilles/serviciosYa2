package com.capgemini.serviciosya.beans.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

public class ConsumerEntity {
	// Map the fields (Database tables ) and properties (Java classes)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, insertable = false, updatable = false)
	private int id;

	@Column(name = "name", length = 48, nullable = false)
	private String name;

	@Column(name = "\"lastName\"", length = 48, nullable = false)
	private String lastName;

	@Column(name = "phone", length = 48, nullable = false, unique = true)
	private String phone;

	@Column(name = "dni", nullable = false, unique = true)
	private Integer dni;

	@Size(min = 15, max = 100)

	@Column(name = "email", length = 128, nullable = false, unique = true)
	private String email;

	@Column(name = "address", length = 128, nullable = false)
	private String address;

	@Column(name = "status", nullable = false)
	private Integer status;

	@ManyToOne
	@JoinColumn(name = "city_id")
	private CityEntity city;

	public ConsumerEntity(int id, String name) {
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public CityEntity getCity() {
		return city;
	}

	public void setCity(CityEntity city) {
		this.city = city;
	}

	@Override
	public String toString() {

		return this.name;
	}
}
