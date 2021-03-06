package com.cts.flights.booking.beans;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class FlightSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int scheduleId;
	private String depertureTime;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate scheduleDate;
	
	/*@ManyToOne(fetch = FetchType.EAGER)
	private Flights flights;*/
	
	public FlightSchedule() {
		super();
	}

	public FlightSchedule(String depertureTime, LocalDate scheduleDate) {
		super();
		this.depertureTime = depertureTime;
		this.scheduleDate = scheduleDate;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getDepertureTime() {
		return depertureTime;
	}

	public void setDepertureTime(String depertureTime) {
		this.depertureTime = depertureTime;
	}

	public LocalDate getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(LocalDate scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	@Override
	public String toString() {
		return "FlightSchedule [scheduleId=" + scheduleId + ", depertureTime=" + depertureTime + ", scheduleDate="
				+ scheduleDate + "]";
	}
}
