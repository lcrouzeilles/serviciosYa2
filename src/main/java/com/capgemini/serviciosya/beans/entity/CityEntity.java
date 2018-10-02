package com.capgemini.serviciosya.beans.entity;

import javax.persistence.*;
@Entity(name = "City")
@Table(name = "city")
public class CityEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "province_seq")
	@SequenceGenerator(name = "province_seq", sequenceName = "province_seq")
	@Column(name = "id", nullable = false, insertable = false, updatable = false)
	private int id;
	@Column(name = "name", length = 48, nullable = false)
	private String name;

	public CityEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CityEntity(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name = "province_id")
	private ProvinceEntity province;
	
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

	public ProvinceEntity getProvince() {
		return province;
	}

	public void setProvince(ProvinceEntity province) {
		this.province = province;
	}

	

}
