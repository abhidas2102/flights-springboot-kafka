package com.cts.flights.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.flights.beans.FlightSchedule;
import com.cts.flights.beans.Flights;
import com.cts.flights.exception.FlightsException;
import com.cts.flights.services.FlightService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("/flights")
public class FlightsAdminController {
	
	@Autowired
	FlightService service;
	
	@ApiImplicitParams({
		  @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class),
	})
	@PostMapping("/admin/saveFlight")
	//@CachePut(cacheNames = "searchFlightByFlightNumber", key = "#flightNumber")
	public void saveFlight(@RequestBody Flights flights) throws FlightsException {
		service.saveFlight(flights);
	}
	
	@ApiImplicitParams({
		  @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class),
	})
	@PostMapping("/admin/scheduleFlight/{flightNumber}")
	//@CachePut(cacheNames = "searchFlightByFlightNumber", key = "#flightNumber")
	public void scheduleFlight(@RequestBody FlightSchedule flightSchedule, @PathVariable String flightNumber) throws FlightsException {
		service.scheduleFlight(flightSchedule,flightNumber);
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
