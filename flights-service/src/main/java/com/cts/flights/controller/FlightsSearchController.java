package com.cts.flights.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.flights.angular.SearchFlightByFlightNumberForm;
import com.cts.flights.angular.SearchFlightsByPersonsForm;
import com.cts.flights.beans.Flights;
import com.cts.flights.exception.FlightsException;
import com.cts.flights.services.FlightService;

//import org.springframework.cache.annotation.Cacheable;

@RestController
@RequestMapping("/flights")
public class FlightsSearchController {
	
	@Autowired
	FlightService service;
	
	@PostMapping("/searchFlightByFlightNumber")
	//@Cacheable(cacheNames = "searchFlightByFlightNumber", key = "#searchFlightByFlightNumberForm.getFlightNumber()")
	public Flights searchFlightByFlightNumber(@RequestBody SearchFlightByFlightNumberForm searchFlightByFlightNumberForm) {
		Flights flights  = service.searchFlightByFlightNumber(searchFlightByFlightNumberForm);
		return flights;
	}

	@PostMapping("/searchFlightByPersons")
	public List<Flights> searchFlightByPersons(@RequestBody SearchFlightsByPersonsForm searchFlightsByPersonsForm) {
		List<Flights> flights  = service.searchFlightsByPersonsForm(searchFlightsByPersonsForm);
		return flights;
	}
	
	@ExceptionHandler({RuntimeException.class})
	public ResponseEntity<String> handleRuntimeException(){
		return new ResponseEntity<String>("NOT FOUND", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({FlightsException.class })
	public ResponseEntity<String> handleFlightsException(){
		return new ResponseEntity<String>("SOMETHING WENT WRONG. PLEASE TRY AGAIN AFTER SOMETIME", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
