package com.cts.flights.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class BookingDetails {
	private String flightNumber;
	
	@Id
	private String pnr;
	private String checkIn="No";
	
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    private List<Passenger> passengers;
	
	public BookingDetails() {
		super();
	}

	public BookingDetails(String flightNumber, String pnr, String checkIn, List<Passenger> passengers) {
		super();
		this.flightNumber = flightNumber;
		this.pnr = pnr;
		this.checkIn = checkIn;
		this.passengers = passengers;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	@Override
	public String toString() {
		return "BookingDetails [flightNumber=" + flightNumber + ", pnr=" + pnr + ", checkIn=" + checkIn
				+ ", passengers=" + passengers + "]";
	}
}
