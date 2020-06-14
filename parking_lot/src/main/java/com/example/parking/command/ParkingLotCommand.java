package com.example.parking.command;

public enum ParkingLotCommand {
	
	CREATE("create_parking_lot"), 
	PARK("park"), 
	UNPARK("leave"), 
	STATUS("status");
	
	private final String value;
	
	private ParkingLotCommand(String value) {
		this.value= value;
	}
	
	public static ParkingLotCommand getCommandByValue(String value) {
		ParkingLotCommand commandType = null;
		for (ParkingLotCommand command : values()) {
			if (command.toString().equals(value)) {
				commandType = command;
				break;
			}
		}
		
		return commandType;
	}
	
	public String toString() {
		return this.value;
	}
	
}
