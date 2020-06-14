package com.example.parking.factory;

import com.example.parking.model.CarSlot;
import com.example.parking.model.IParkingLotState;
import com.example.parking.model.ParkingLevel;
import com.example.parking.model.ParkingLotState;

public class ParkingSlotStateFactory {
	
	public static IParkingLotState createOneLevelCarParkingSlotState(int slotsCount) {
		IParkingLotState parkingSlotState = new ParkingLotState();
		
		parkingSlotState.getParkingLevels().add(null);
		parkingSlotState.getParkingLevels().add(new ParkingLevel());
		
		final int parkingLevel = 1; 
		for (int slotIdx = 0 ; slotIdx <= slotsCount ; slotIdx++) {
			if (slotIdx == 0) {
				parkingSlotState.getParkingLevels().get(parkingLevel).getSlots().add(null);
				continue;
			}
			parkingSlotState.getParkingLevels().get(parkingLevel).getSlots().add(new CarSlot(slotIdx, true));
		}
		
		return parkingSlotState;
	}
	
}
