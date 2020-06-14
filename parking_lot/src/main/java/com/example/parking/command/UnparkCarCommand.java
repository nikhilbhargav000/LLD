package com.example.parking.command;

import com.example.parking.model.CarVehicle;
import com.example.parking.system.IParkingSystemManager;

public class UnparkCarCommand implements Command {
	
	private int parkingHours;
	private CarVehicle car;
	private IParkingSystemManager parkingSystemManager;
	
	public UnparkCarCommand(int parkingHours, CarVehicle car, IParkingSystemManager parkingSystemManager) {
		super();
		this.parkingHours = parkingHours;
		this.car = car;
		this.parkingSystemManager = parkingSystemManager;
	}

	public void execute() {
		parkingSystemManager.unpark(car, parkingSystemManager.getParkingLotState(), parkingHours);
		
		return;
	}
}
