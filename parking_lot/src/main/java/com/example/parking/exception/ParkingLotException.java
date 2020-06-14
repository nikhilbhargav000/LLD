package com.example.parking.exception;

public class ParkingLotException extends RuntimeException {

	private static final long serialVersionUID = 2275900196367204065L;
	
	public ParkingLotException(String msg) {
		super(msg);
	}
	
}
