package com.capgemini.serviciosya.beans.domain;

import java.time.LocalDateTime;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Availability.java
//  @ Date : 11/09/2018
//  @ Author : 
//
//




public class Availability {
	private String dayOfTheWeek;
	private LocalDateTime startingHour;
	private LocalDateTime endingHour;
	public Availability(String dayOfTheWeek, LocalDateTime startingHour, LocalDateTime endingHour) {
		super();
		this.dayOfTheWeek = dayOfTheWeek;
		this.startingHour = startingHour;
		this.endingHour = endingHour;
	}
	public String getDayOfTheWeek() {
		return dayOfTheWeek;
	}
	public void setDayOfTheWeek(String dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
	}
	public LocalDateTime getStartingHour() {
		return startingHour;
	}
	public void setStartingHour(LocalDateTime startingHour) {
		this.startingHour = startingHour;
	}
	public LocalDateTime getEndingHour() {
		return endingHour;
	}
	public void setEndingHour(LocalDateTime endingHour) {
		this.endingHour = endingHour;
	}
}