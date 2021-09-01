package com.cts.flights.booking.angular;

import java.time.LocalDate;

public class SearchFlightByFlightNumberForm {
	private String flightNumber;
	private LocalDate flightDate;
	
	public SearchFlightByFlightNumberForm() {
		super();
	}

	public SearchFlightByFlightNumberForm(String flightNumber, LocalDate flightDate) {
		super();
		this.flightNumber = flightNumber;
		this.flightDate = flightDate;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public LocalDate getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(LocalDate flightDate) {
		this.flightDate = flightDate;
	}

	@Override
	public String toString() {
		return "SearchFlightByFlightNumberForm [flightNumber=" + flightNumber + ", flightDate=" + flightDate + "]";
	}
}
