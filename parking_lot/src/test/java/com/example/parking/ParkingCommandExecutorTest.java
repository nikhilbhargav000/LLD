package com.example.parking;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.example.parking.command.ParkingLotCommand;
import com.example.parking.executor.ParkingCommandExecutor;

public class ParkingCommandExecutorTest {

	@Test
	public void test1() {
		ParkingCommandExecutor excecutor = new ParkingCommandExecutor();
		
		String[] commandArr = {"create_parking_lot", "6"};
		ParkingLotCommand command = excecutor.getParkingLotCommand(commandArr);

		assertTrue(ParkingLotCommand.CREATE.toString().equals(command.toString()));
	}
	
	@Test
	public void test2() {
		ParkingCommandExecutor excecutor = new ParkingCommandExecutor();
		
		String[] commandArr = {"park", "KA-01-HH-2701"};
		ParkingLotCommand command = excecutor.getParkingLotCommand(commandArr);
		
		assertTrue(ParkingLotCommand.PARK.toString().equals(command.toString()));
	}
	
	@Test
	public void test3() {
		ParkingCommandExecutor excecutor = new ParkingCommandExecutor();
		
		String[] commandArr = {"leave", "KA-01-HH-3141", "4"};
		ParkingLotCommand command = excecutor.getParkingLotCommand(commandArr);
		
		assertTrue(ParkingLotCommand.UNPARK.toString().equals(command.toString()));
	}
	
	@Test
	public void test4() {
		ParkingCommandExecutor excecutor = new ParkingCommandExecutor();
		
		String[] commandArr = {"status"};
		ParkingLotCommand command = excecutor.getParkingLotCommand(commandArr);
		
		assertTrue(ParkingLotCommand.STATUS.toString().equals(command.toString()));
	}
	
}
