package com.cts.flights.booking.services;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.cts.flights.booking.angular.SearchFlightByFlightNumberForm;
import com.cts.flights.booking.angular.ShowBookingDetails;
import com.cts.flights.booking.beans.BookingDetails;
import com.cts.flights.booking.beans.Flights;
import com.cts.flights.booking.beans.Passenger;
import com.cts.flights.booking.jwt.User;
import com.cts.flights.booking.jwt.UserRepository;
import com.cts.flights.booking.proxy.FlightServiceProxy;
import com.cts.flights.booking.repositories.BookingDetailsRepository;

@Service
public class BookingDetailsService {
	
	@Autowired
	BookingDetailsRepository repo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	FlightServiceProxy flightProxy;
	
	@Autowired
    private KafkaTemplate<String, Flights> kafkaTemplate;
	
	public BookingDetails saveBookingDetails(String flightNumber, String date, String username,
			List<Passenger> passengers) {
		User user = userRepo.getUserByUsername(username).get();
		
		String pnr = user.getMobile()+flightNumber+date.replace("-", "");
		
		BookingDetails bookingDetails= new BookingDetails();
		bookingDetails.setFlightNumber(flightNumber);
		bookingDetails.setPnr(pnr);
		
		Iterator<BookingDetails> itr = user.getBookingDetails().iterator();
		while(itr.hasNext()) {
			BookingDetails bD = itr.next();
			if(bD.getPnr().equals(pnr)) {
				System.out.println("Already Booked");
				return bookingDetails;
			}
		}
		
		bookingDetails.setCheckIn("No");
		bookingDetails.setPassengers(passengers);
		user.getBookingDetails().add(bookingDetails);
		
		Flights flights = flightProxy.searchFlightByFlightNumberOnly(bookingDetails.getFlightNumber());
		if(flights.getAvailableSeats() > passengers.size()) {
			flights.setAvailableSeats(flights.getAvailableSeats()-passengers.size());
			userRepo.save(user);
			flightProxy.saveFlight(flights);
			Message<Flights> message = MessageBuilder
	                .withPayload(flights)
	                .setHeader(KafkaHeaders.TOPIC, "flights")
	                .setHeader("Content-Type", "application/json")
	                .build();

	        kafkaTemplate.send(message);
		}
		
		return bookingDetails; 
	}
	
	public ShowBookingDetails showBookingDetails(String pnr) {
		
		BookingDetails bookingDetails = repo.findById(pnr).get();
		
		List<Passenger> passengers = bookingDetails.getPassengers();
		
		String tempPNR = pnr.substring(pnr.length()-8);
		tempPNR = tempPNR.substring(0,4) + "-" + tempPNR.substring(4,6) + "-"+ tempPNR.substring(6);
		
		LocalDate date = LocalDate.parse(tempPNR);
		
		SearchFlightByFlightNumberForm searchFlightByFlightNumberForm = 
				new SearchFlightByFlightNumberForm(bookingDetails.getFlightNumber(),date);
				
		Flights flights = flightProxy.searchFlightByFlightNumber(searchFlightByFlightNumberForm);
		
		ShowBookingDetails showBookingDetails = new ShowBookingDetails();
		showBookingDetails.setPnr(pnr);
		showBookingDetails.setFlightNumber(flights.getFlightNumber());
		showBookingDetails.setAirlineName(flights.getAirlineName());
		showBookingDetails.setBookingStatus(bookingDetails.getCheckIn());
		showBookingDetails.setDuration(flights.getDuration());
		showBookingDetails.setPassengers(passengers);
		showBookingDetails.setDepertureTime(flights.getFlightSchedule().get(0).getDepertureTime());
		showBookingDetails.setDepertureDate(date);
		
		return showBookingDetails;
	}

	public void cancelBooking(String pnr,String username) {
		User user = userRepo.getUserByUsername(username).get();
		
		BookingDetails bookingDetails = repo.findById(pnr).get();
		
		Flights flights = flightProxy.searchFlightByFlightNumberOnly(bookingDetails.getFlightNumber());
		
		flights.setAvailableSeats(flights.getAvailableSeats()+bookingDetails.getPassengers().size());
		
		Iterator<BookingDetails> itr = user.getBookingDetails().iterator();
		while(itr.hasNext()) {
			BookingDetails bD = itr.next();
			if(bD.getPnr().equals(pnr)) {
				itr.remove();
			}
		}
		user.setBookingDetails(user.getBookingDetails());
		//user.getBookingDetails().remove(bookingDetails);
		userRepo.save(user);
		repo.deleteById(pnr);
		
		flightProxy.saveFlight(flights);
		
		/*List<Header> headers = new ArrayList()<>();
        headers.add(new RecordHeader(("Content-Type", "application/json")));
		ProducerRecord<String, String> bar = new ProducerRecord<>("flights", null, null, null, flights, null)*/
		Message<Flights> message = MessageBuilder
                .withPayload(flights)
                .setHeader(KafkaHeaders.TOPIC, "flights")
                .setHeader("Content-Type", "application/json")
                .build();

        kafkaTemplate.send(message);
		//kafkaTemplate.send("flights", message);
	}

	public void doCheckIn(String pnr) {
		BookingDetails bookingDetails = repo.findById(pnr).get();
		if(bookingDetails.getCheckIn().equalsIgnoreCase("No")) {
			bookingDetails.setCheckIn("Yes");
			repo.save(bookingDetails);
		}
	}

	public List<BookingDetails> bookingHistory(String username) {
		User user = userRepo.getUserByUsername(username).get();
		return user.getBookingDetails();
	}

}
