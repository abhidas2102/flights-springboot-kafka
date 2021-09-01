package com.cts.flights.booking.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cts.flights.booking.angular.SearchFlightByFlightNumberForm;
import com.cts.flights.booking.beans.Flights;

@FeignClient(name="flights-api", url="localhost:8080")
public interface FlightServiceProxy {
	
	@PostMapping("/flights/searchFlightByFlightNumber")
	public Flights searchFlightByFlightNumber(@RequestBody SearchFlightByFlightNumberForm searchFlightByFlightNumberForm);

	@GetMapping("/flights/searchFlightByFlightNumber/{flightNumber}")
	public Flights searchFlightByFlightNumberOnly(@PathVariable String flightNumber);

	@PostMapping("/flights/saveFlight")
	public void saveFlight(@RequestBody Flights flights);
}
