package com.cts.flights.services;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cts.flights.angular.SearchFlightByFlightNumberForm;
import com.cts.flights.beans.FlightSchedule;
import com.cts.flights.beans.Flights;
import com.cts.flights.repositories.FlightsRepository;

@SpringBootTest
public class FlightsServiceTest {
	
	@Autowired
	FlightService flightService;
	
	@MockBean
    private FlightsRepository flightRepository;
	
	@Test
	public void searchFlightByFlightNumberTest() {
		List<FlightSchedule> fS = new ArrayList<>();
		Optional<Flights> flights = Optional.of(new Flights("IN1234", "INDIGO", "2:00", 2000, 100, "Kolkata", "Bangalore", fS));
		
		Mockito
        .when(flightRepository.findById("IN1234"))
        .thenReturn(flights);
		
		Flights flightsResponse =  flightService.searchFlightByFlightNumber("IN1234");
		assertNotNull(flightsResponse);
	}
	
	@Test
	public void searchFlightByFlightNumberFormTest() {
		LocalDate date = LocalDate.now();
		SearchFlightByFlightNumberForm searchFlightByFlightNumberForm = 
				new SearchFlightByFlightNumberForm("IN1234", date);
		
		List<FlightSchedule> fS = new ArrayList<>();
		fS.add(new FlightSchedule("12:00", date));
		
		Optional<Flights> flights = Optional.of(new Flights("IN1234", "INDIGO", "2:00", 2000, 100, "Kolkata", "Bangalore", fS));
		
		
		Mockito
        .when(flightRepository.findById("IN1234"))
        .thenReturn(flights);
		
		Flights flightsResponse = flightService.searchFlightByFlightNumber(searchFlightByFlightNumberForm);
		
		assertNotNull(flightsResponse);
	}
}
