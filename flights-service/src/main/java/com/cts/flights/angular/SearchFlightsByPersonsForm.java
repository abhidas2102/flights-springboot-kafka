package com.cts.flights.angular;

import java.time.LocalDate;

public class SearchFlightsByPersonsForm {
	private String origin;
	private String destination;
	private LocalDate flightDate;
	private int persons;
	public SearchFlightsByPersonsForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SearchFlightsByPersonsForm(String origin, String destination, LocalDate flightDate, int persons) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.flightDate = flightDate;
		this.persons = persons;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public LocalDate getFlightDate() {
		return flightDate;
	}
	public void setFlightDate(LocalDate flightDate) {
		this.flightDate = flightDate;
	}
	
	public int getPersons() {
		return persons;
	}
	public void setPersons(int persons) {
		this.persons = persons;
	}
	@Override
	public String toString() {
		return "SearchFlightsByPersonsForm [origin=" + origin + ", destination=" + destination + ", flightDate=" + flightDate
				+ ", persons=" + persons + "]";
	}
}
