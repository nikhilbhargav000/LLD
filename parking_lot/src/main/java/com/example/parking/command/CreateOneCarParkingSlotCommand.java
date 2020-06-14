package com.example.parking.command;

import com.example.parking.exception.ParkingLotException;
import com.example.parking.factory.ParkingSlotStateFactory;
import com.example.parking.model.IParkingLotState;
import com.example.parking.system.IParkingSystemManager;

public class CreateOneCarParkingSlotCommand implements Command {

	private int parkingSlotsCount;
	private IParkingSystemManager parkingSystemManager;
	
	public CreateOneCarParkingSlotCommand(int parkingSlotsCount, IParkingSystemManager parkingSystemManager) {
		this.parkingSlotsCount = parkingSlotsCount;
		this.parkingSystemManager = parkingSystemManager;
	}
	
	public void execute() {
		if (parkingSystemManager.getParkingLotState() != null) {
			throw new ParkingLotException("Parking lot already created");
		}

		IParkingLotState parkingLotState = ParkingSlotStateFactory.createOneLevelCarParkingSlotState(this.parkingSlotsCount);
		parkingSystemManager.setParkingLotState(parkingLotState);

		System.out.println("Created parking lot with " + this.parkingSlotsCount + " slots");
	}
	
}
