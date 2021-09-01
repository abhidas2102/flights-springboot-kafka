package com.cts.flights.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.flights.beans.Flights;

@Repository
public interface FlightsRepository extends JpaRepository<Flights, String> {
	public List<Flights> findBySourceAndDestinationAndAvailableSeatsGreaterThanEqual
	(String source,String destination,int avaiableSeats);
}
