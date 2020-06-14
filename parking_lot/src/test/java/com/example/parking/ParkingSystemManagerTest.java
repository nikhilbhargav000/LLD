package com.example.parking;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.example.parking.exception.ParkingLotException;
import com.example.parking.factory.ParkingSlotStateFactory;
import com.example.parking.model.CarVehicle;
import com.example.parking.system.ParkingSystemManager;

public class ParkingSystemManagerTest {
	
	private ParkingSystemManager parkingManager;
	private final int PARKING_SLOT_COUNT = 5;
	
	@Before
	public void init() {
		parkingManager = ParkingSystemManager.getInstance();
	}

	@Test
	public void test1() {
		
		parkingManager = ParkingSystemManager.getInstance();
		parkingManager.setParkingLotState(ParkingSlotStateFactory.createOneLevelCarParkingSlotState(PARKING_SLOT_COUNT));
		
		parkingManager.park(new CarVehicle("PB 10 2311"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("KA 10 2255"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("KA 10 8888"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("KA 10 2233"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("PB 99 8989"), parkingManager.getParkingLotState());
		
		assertFalse(parkingManager.park(new CarVehicle("PB 08 2233"), parkingManager.getParkingLotState()));
		
	}
	
	@Test
	public void test2() {
		
		parkingManager = ParkingSystemManager.getInstance();
		parkingManager.setParkingLotState(ParkingSlotStateFactory.createOneLevelCarParkingSlotState(PARKING_SLOT_COUNT));
		
		parkingManager.park(new CarVehicle("PB 10 2311"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("KA 10 2233"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("PB 99 8989"), parkingManager.getParkingLotState());
		
		assertTrue(parkingManager.park(new CarVehicle("PB 08 2233"), parkingManager.getParkingLotState()));
		
	}
	
	@Test
	public void test3() {
		
		parkingManager = ParkingSystemManager.getInstance();
		parkingManager.setParkingLotState(ParkingSlotStateFactory.createOneLevelCarParkingSlotState(PARKING_SLOT_COUNT));
		
		parkingManager.park(new CarVehicle("PB 10 2311"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("KA 10 2255"), parkingManager.getParkingLotState());
		
		assert(parkingManager.unpark(new CarVehicle("KA 10 2255"), parkingManager.getParkingLotState(), 1));
		
	}
	
	@Test
	public void test4() {
		
		parkingManager = ParkingSystemManager.getInstance();
		parkingManager.setParkingLotState(ParkingSlotStateFactory.createOneLevelCarParkingSlotState(PARKING_SLOT_COUNT));
		
		parkingManager.park(new CarVehicle("PB 10 2311"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("KA 10 2255"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("PB 99 8989"), parkingManager.getParkingLotState());
		
		assertTrue(parkingManager.unpark(new CarVehicle("KA 10 2255"), parkingManager.getParkingLotState(), 15));
		
	}

	@Test(expected=ParkingLotException.class)
	public void test5() {
		
		parkingManager = ParkingSystemManager.getInstance();
		parkingManager.setParkingLotState(ParkingSlotStateFactory.createOneLevelCarParkingSlotState(PARKING_SLOT_COUNT));
		
		parkingManager.park(new CarVehicle("PB 10 2311"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("KA 10 2255"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("PB 99 8989"), parkingManager.getParkingLotState());
		
		assertFalse(parkingManager.unpark(new CarVehicle("KA 10 XXXX"), parkingManager.getParkingLotState(), 1));
		
	}
	

	@Test
	public void test6() {
		
		parkingManager = ParkingSystemManager.getInstance();
		parkingManager.setParkingLotState(ParkingSlotStateFactory.createOneLevelCarParkingSlotState(PARKING_SLOT_COUNT));
		
		parkingManager.park(new CarVehicle("PB 10 2311"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("KA 10 2255"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("KA 10 2255"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("KA 10 2233"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("PB 99 8989"), parkingManager.getParkingLotState());
		
		assertTrue(parkingManager.unpark(new CarVehicle("KA 10 2255"), parkingManager.getParkingLotState(), 1));
		
	}
	
	@Test
	public void test7() {
		
		parkingManager = ParkingSystemManager.getInstance();
		parkingManager.setParkingLotState(ParkingSlotStateFactory.createOneLevelCarParkingSlotState(PARKING_SLOT_COUNT));
		
		parkingManager.park(new CarVehicle("PB 10 2311"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("KA 10 2255"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("KA 10 2233"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("PB 99 8989"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("PB 99 1212"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("PB 99 7878"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("PB 99 3434"), parkingManager.getParkingLotState());
		
		assertTrue(parkingManager.unpark(new CarVehicle("KA 10 2255"), parkingManager.getParkingLotState(), 1));
		
	}
	
	@Test
	public void test8() {
		
		parkingManager = ParkingSystemManager.getInstance();
		parkingManager.setParkingLotState(ParkingSlotStateFactory.createOneLevelCarParkingSlotState(PARKING_SLOT_COUNT));
		
		parkingManager.park(new CarVehicle("PB 10 2311"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("KA 10 2255"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("PB 99 8989"), parkingManager.getParkingLotState());
		parkingManager.unpark(new CarVehicle("KA 10 2255"), parkingManager.getParkingLotState(), 15);
		
		String status = parkingManager.printLevelOneStatus(parkingManager.getParkingLotState());
		
		String exceptedStatus = "Slot No.    Registration No.\n" + 
				"1           PB 10 2311\n" + 
				"3           PB 99 8989\n";
		
		assertTrue(status.equals(exceptedStatus));
		
	}
	
	@Test
	public void test9() {
		
		parkingManager = ParkingSystemManager.getInstance();
		parkingManager.setParkingLotState(ParkingSlotStateFactory.createOneLevelCarParkingSlotState(PARKING_SLOT_COUNT));
		
		parkingManager.park(new CarVehicle("PB 10 2311"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("KA 10 2255"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("KA 10 2233"), parkingManager.getParkingLotState());
		parkingManager.park(new CarVehicle("PB 99 8989"), parkingManager.getParkingLotState());
		parkingManager.unpark(new CarVehicle("PB 10 2311"), parkingManager.getParkingLotState(), 6);
		parkingManager.unpark(new CarVehicle("KA 10 2233"), parkingManager.getParkingLotState(), 4);
		parkingManager.park(new CarVehicle("PB 99 1212"), parkingManager.getParkingLotState());
		parkingManager.unpark(new CarVehicle("PB 99 8989"), parkingManager.getParkingLotState(), 6);
		parkingManager.park(new CarVehicle("PB 99 3434"), parkingManager.getParkingLotState());
		
		String status = parkingManager.printLevelOneStatus(parkingManager.getParkingLotState());
		String exceptedStatus = "Slot No.    Registration No.\n" + 
				"1           PB 99 1212\n" + 
				"2           KA 10 2255\n" + 
				"3           PB 99 3434\n";
		
		assertTrue(status.equals(exceptedStatus));
		
	}
	
	
}
