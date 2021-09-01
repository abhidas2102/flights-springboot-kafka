package com.cts.flights.services;


import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.flights.angular.SearchFlightByFlightNumberForm;
import com.cts.flights.angular.SearchFlightsByPersonsForm;
import com.cts.flights.beans.FlightSchedule;
import com.cts.flights.beans.Flights;
import com.cts.flights.exception.FlightsException;
import com.cts.flights.repositories.FlightsRepository;


@Service
public class FlightService {
	
	@Autowired
	FlightsRepository repo;

	public Flights searchFlightByFlightNumber(String flightNumber) {
		Flights flights  = repo.findById(flightNumber).get();
		return flights;
	}

	public Flights searchFlightByFlightNumber(SearchFlightByFlightNumberForm searchFlightByFlightNumberForm) {

		LocalDate date = searchFlightByFlightNumberForm.getFlightDate();
		Flights flights  = repo.findById(searchFlightByFlightNumberForm.getFlightNumber()).get();
		
		List<FlightSchedule> flightSchedulesFiltered = flights.getFlightSchedule().stream().
				filter((FlightSchedule flightSchedule) ->  {
					
					LocalDate scheDate = flightSchedule.getScheduleDate();
					if(date.equals(scheDate)) {
						return true;
					}
					return false;
					
				}).toList();
		
		flights.setFlightSchedule(flightSchedulesFiltered);
		
		/*if(flights.getFlightSchedule().isEmpty()) {
			flights=null;
		}*/
		
		return flights;
	}

	public List<Flights> searchFlightsByPersonsForm(SearchFlightsByPersonsForm searchFlightsByPersonsForm) {
		LocalDate date = searchFlightsByPersonsForm.getFlightDate();
		
		List<Flights> flights  = repo.findBySourceAndDestinationAndAvailableSeatsGreaterThanEqual(
				searchFlightsByPersonsForm.getOrigin(),searchFlightsByPersonsForm.getDestination(),
				searchFlightsByPersonsForm.getPersons());
		
		Iterator<Flights> itr = flights.iterator();
		while(itr.hasNext()) {
			Flights flight = itr.next();
			
			List<FlightSchedule> flightSchedulesFiltered = flight.getFlightSchedule().stream().
					filter((FlightSchedule flightSchedule) ->  {
						
						LocalDate scheDate = flightSchedule.getScheduleDate();
						if(date.equals(scheDate)) {
							return true;
						}
						return false;
						
					}).toList();
			
			flight.setFlightSchedule(flightSchedulesFiltered);
			
		}
		
		Iterator<Flights> itr2 = flights.iterator();
		while(itr2.hasNext()) {
			Flights flight = itr2.next();
			if(flight.getFlightSchedule().isEmpty()) {
				itr2.remove();
			}
		}
		
		return flights;
				
	}

	public void saveFlight(Flights flights) throws FlightsException {
		try {
			repo.save(flights);
		} catch (Exception e) {
			throw new FlightsException(e);
		}
	}

	public void scheduleFlight(FlightSchedule flightSchedule, String flightNumber) throws FlightsException {
		try {
			Flights flight = repo.findById(flightNumber).get();
			flight.getFlightSchedule().add(flightSchedule);
			repo.save(flight);
		} catch (Exception e) {
			throw new FlightsException(e);
		}
	}

}
