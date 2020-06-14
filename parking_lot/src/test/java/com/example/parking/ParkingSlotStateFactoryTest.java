package com.example.parking;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.example.parking.factory.ParkingSlotStateFactory;
import com.example.parking.model.CarSlot;
import com.example.parking.model.IParkingLotState;

public class ParkingSlotStateFactoryTest {

	@Test
	public void test1() {
		
		int parkingSlotCount = 6;
		IParkingLotState parking = ParkingSlotStateFactory.createOneLevelCarParkingSlotState(parkingSlotCount);
		
		assertTrue(parking.getParkingLevels().get(1).getSlots().size() == (parkingSlotCount + 1));
		
	}

	@Test
	public void test2() {
		
		int parkingSlotCount = 6;
		IParkingLotState parking = ParkingSlotStateFactory.createOneLevelCarParkingSlotState(parkingSlotCount);
		
		assertTrue(parking.getParkingLevels().get(1).getSlots().get(1) instanceof CarSlot);
	}
	
}
