package com.cts.flights.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.flights.beans.Flights;
import com.cts.flights.exception.FlightsException;
import com.cts.flights.services.FlightService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

//import org.springframework.cache.annotation.CachePut;

@RestController
@RequestMapping("/flights")
public class FlightsController {
	
	@Autowired
	FlightService service;
	
	@KafkaListener(topics = "flights", groupId="group_id")
    public void consumeJson(String flights) throws FlightsException, JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
		Flights flightsObj = objectMapper.readValue(flights, Flights.class);
		service.saveFlight(flightsObj);
    }
	
	@GetMapping("/searchFlightByFlightNumber/{flightNumber}")
	public Flights searchFlightByFlightNumber(@PathVariable String flightNumber) {
		Flights flights  = service.searchFlightByFlightNumber(flightNumber);
		return flights;
	}
	
	@ApiImplicitParams({
		  @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class),
	})
	@PostMapping("/saveFlight")
	//@CachePut(cacheNames = "searchFlightByFlightNumber", key = "#flights.getFlightNumber()")
	public void saveFlight(@RequestBody Flights flights) throws FlightsException {
		service.saveFlight(flights);
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
