package com.cts.flights.booking.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cts.flights.booking.beans.BookingDetails;
import com.cts.flights.booking.beans.Passenger;
import com.cts.flights.booking.jwt.Role;
import com.cts.flights.booking.jwt.User;
import com.cts.flights.booking.jwt.UserRepository;

@SpringBootTest
public class BookingDetailsServiceTest {

	@Autowired
	BookingDetailsService bookingDetailsService;
	
	@MockBean
    private UserRepository userRepository;
	
	@Test
	public void bookingHistoryTest() {
		
		Set<Role> roles = new HashSet<>();
		roles.add(new Role(1, "USER"));
		
		List<Passenger> p = new ArrayList<>();
		p.add(new Passenger(1L, "JOHN", "DOE", "Male", 20));
		
		List<BookingDetails> bD = new ArrayList<>();
		bD.add(new BookingDetails("IN1234", "PNR", "No", p));
		
		Optional<User> user = Optional.of(new User(1L, "JOHN", "JOHN", 9000000000L, "j@j.com", true, roles, bD));
		
		Mockito
        .when(userRepository.getUserByUsername("JOHN"))
        .thenReturn(user);
		
		List<BookingDetails> bDRes = bookingDetailsService.bookingHistory("JOHN");
		
		assertNotNull(bDRes);
		
	}
}
