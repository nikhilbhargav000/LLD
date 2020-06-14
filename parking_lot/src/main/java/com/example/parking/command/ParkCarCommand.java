package com.example.parking.command;

import com.example.parking.model.CarVehicle;
import com.example.parking.system.IParkingSystemManager;

public class ParkCarCommand implements Command {

	private CarVehicle car;
	private IParkingSystemManager parkingSystemManager;
	
	public ParkCarCommand(CarVehicle car, IParkingSystemManager parkingSystemManager) {
		super();
		this.car = car;
		this.parkingSystemManager = parkingSystemManager;
	}

	public void execute() {
		parkingSystemManager.park(car, this.parkingSystemManager.getParkingLotState());
		return;
	}

}
