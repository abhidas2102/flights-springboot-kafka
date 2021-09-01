package com.cts.flights.booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.flights.booking.beans.BookingDetails;


@Repository
public interface BookingDetailsRepository extends JpaRepository<BookingDetails, String> {

}
