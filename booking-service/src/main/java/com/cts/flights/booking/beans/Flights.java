package com.cts.flights.booking.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Flights {
	
	@Id
	private String flightNumber;
	private String airlineName;
	private String duration;
	private float fare;
	private int availableSeats;
	private String source;
	private String destination;
	
	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<FlightSchedule> flightSchedule;
	
	public Flights() {
		super();
	}

	public Flights(String flightNumber, String airlineName, String duration, float fare, int availableSeats,
			String source, String destination, List<FlightSchedule> flightSchedule) {
		super();
		this.flightNumber = flightNumber;
		this.airlineName = airlineName;
		this.duration = duration;
		this.fare = fare;
		this.availableSeats = availableSeats;
		this.source = source;
		this.destination = destination;
		this.flightSchedule = flightSchedule;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public float getFare() {
		return fare;
	}

	public void setFare(float fare) {
		this.fare = fare;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public List<FlightSchedule> getFlightSchedule() {
		return flightSchedule;
	}

	public void setFlightSchedule(List<FlightSchedule> flightSchedule) {
		this.flightSchedule = flightSchedule;
	}

	@Override
	public String toString() {
		return "Flights [flightNumber=" + flightNumber + ", airlineName=" + airlineName + ", duration=" + duration
				+ ", fare=" + fare + ", availableSeats=" + availableSeats + ", source=" + source + ", destination="
				+ destination + ", flightSchedule=" + flightSchedule + "]";
	}
}
