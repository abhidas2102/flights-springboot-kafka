package com.cts.flights.booking.exception;

public class FlightsException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public FlightsException() {
        super();
    }
    public FlightsException(String message, Throwable cause) {
        super(message, cause);
    }
    public FlightsException(String message) {
        super(message);
    }
    public FlightsException(Throwable cause) {
        super(cause);
    }
}