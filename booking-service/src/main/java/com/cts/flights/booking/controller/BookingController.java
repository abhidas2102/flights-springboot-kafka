package com.cts.flights.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.flights.booking.angular.ShowBookingDetails;
import com.cts.flights.booking.beans.BookingDetails;
import com.cts.flights.booking.beans.Passenger;
import com.cts.flights.booking.exception.FlightsException;
import com.cts.flights.booking.services.BookingDetailsService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	BookingDetailsService bookingService;
	
	@ApiImplicitParams({
		  @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class),
	})
	@PostMapping("/saveBooking/{flightNumber}/{date}/{username}")
	public BookingDetails  saveBooking(@RequestBody List<Passenger> passengers, 
			@PathVariable String flightNumber, @PathVariable String date, @PathVariable String username) throws FlightsException {
		BookingDetails bD = bookingService.saveBookingDetails(flightNumber,date, username,passengers);
		return bD;
	}
	
	@ApiImplicitParams({
		  @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class),
	})
	@GetMapping("/getBookingDetails/{pnr}")
	public ShowBookingDetails showBookingDetails(@PathVariable String pnr) {
		return bookingService.showBookingDetails(pnr);
	
	}
	
	@ApiImplicitParams({
		  @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class),
	})
	@GetMapping("/cancelBooking/{pnr}/{username}")
	public void cancelBooking(@PathVariable String pnr, @PathVariable String username) {
		bookingService.cancelBooking(pnr,username);
	}
	
	@ApiImplicitParams({
		  @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class),
	})
	@GetMapping("/doCheckIn/{pnr}")
	public void doCheckIn(@PathVariable String pnr) {
		bookingService.doCheckIn(pnr);
	}
	
	@ApiImplicitParams({
		  @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, allowEmptyValue = false, paramType = "header", dataTypeClass = String.class),
	})
	@GetMapping("/bookingHistory/{username}")
	public List<BookingDetails> bookingHistory(@PathVariable String username) {
		return bookingService.bookingHistory(username);
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
