package com.example.parking.command;

import com.example.parking.system.IParkingSystemManager;

public class ParkingStatusLevelOneCommand implements Command {

	private IParkingSystemManager parkingSystemManager;

	public ParkingStatusLevelOneCommand(IParkingSystemManager parkingSystemManager) {
		super();
		this.parkingSystemManager = parkingSystemManager;
	}

	public void execute() {
		parkingSystemManager.printLevelOneStatus(parkingSystemManager.getParkingLotState());
		
		return;
	}
	
}
