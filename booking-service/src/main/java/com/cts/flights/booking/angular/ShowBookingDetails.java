package com.cts.flights.booking.angular;

import java.time.LocalDate;
import java.util.List;

import com.cts.flights.booking.beans.Passenger;

public class ShowBookingDetails {
	private String flightNumber;
	private String airlineName;
	private String date;
	private String pnr;
	private String duration;
	private String bookingStatus;
	private String depertureTime;
	private LocalDate depertureDate;
	private List<Passenger> passengers;
	
	public ShowBookingDetails() {
		super();
	}

	public ShowBookingDetails(String flightNumber, String airlineName, String date, String pnr, String duration,
			String bookingStatus, String depertureTime, LocalDate depertureDate, List<Passenger> passengers) {
		super();
		this.flightNumber = flightNumber;
		this.airlineName = airlineName;
		this.date = date;
		this.pnr = pnr;
		this.duration = duration;
		this.bookingStatus = bookingStatus;
		this.depertureTime = depertureTime;
		this.depertureDate = depertureDate;
		this.passengers = passengers;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getDepertureTime() {
		return depertureTime;
	}

	public void setDepertureTime(String depertureTime) {
		this.depertureTime = depertureTime;
	}

	public LocalDate getDepertureDate() {
		return depertureDate;
	}

	public void setDepertureDate(LocalDate depertureDate) {
		this.depertureDate = depertureDate;
	}

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	@Override
	public String toString() {
		return "ShowBookingDetails [flightNumber=" + flightNumber + ", airlineName=" + airlineName + ", date=" + date
				+ ", pnr=" + pnr + ", duration=" + duration + ", bookingStatus=" + bookingStatus + ", depertureTime="
				+ depertureTime + ", depertureDate=" + depertureDate + ", passengers=" + passengers + "]";
	}
}	