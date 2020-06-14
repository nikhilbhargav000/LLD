package com.example.parking.command;

import com.example.parking.exception.ParkingLotException;

public class ParkingCommandInvoker {

	private Command command;
	
	public ParkingCommandInvoker(Command c){
		this.command=c;
	}
	
	public void execute(){
		try {
			this.command.execute();
		} catch (ParkingLotException e) {
			System.out.println(e.getMessage());
		}
	}
}
