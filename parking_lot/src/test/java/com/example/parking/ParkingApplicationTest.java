package com.example.parking;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.parking.ParkingApplication;

public class ParkingApplicationTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}

	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	}
	
	@Test
	public void test1() {
		ParkingApplication parkingApp = new ParkingApplication();
		
		List<String> commands = new ArrayList<String>();
		commands.add("create_parking_lot 6");
		commands.add("park KA-01-HH-1234");
		commands.add("park KA-01-HH-9999");
		commands.add("park KA-01-BB-0001");
		commands.add("park KA-01-HH-7777");
		commands.add("park KA-01-HH-2701");
		commands.add("park KA-01-HH-3141");
		commands.add("leave KA-01-HH-3141 4");
		commands.add("status");
		commands.add("park KA-01-P-333");
		commands.add("park DL-12-AA-9999");
		commands.add("leave KA-01-HH-1234 4");
		commands.add("leave KA-01-BB-0001 6");
		commands.add("leave DL-12-AA-9999 2");
		commands.add("park KA-09-HH-0987");
		commands.add("park CA-09-IO-1111");
		commands.add("park KA-09-HH-0123");
		commands.add("status");

		parkingApp.executeCommands(commands);
		
		String expectedOutput = "Created parking lot with 6 slots\n" + 
				"Allocated slot number: 1\n" + 
				"Allocated slot number: 2\n" + 
				"Allocated slot number: 3\n" + 
				"Allocated slot number: 4\n" + 
				"Allocated slot number: 5\n" + 
				"Allocated slot number: 6\n" + 
				"Registration number KA-01-HH-3141 with Slot Number 6 is free with Charge 30\n" + 
				"Slot No.    Registration No.\n" + 
				"1           KA-01-HH-1234\n" + 
				"2           KA-01-HH-9999\n" + 
				"3           KA-01-BB-0001\n" + 
				"4           KA-01-HH-7777\n" + 
				"5           KA-01-HH-2701\n" + 
				"Allocated slot number: 6\n" + 
				"Sorry, parking lot is full\n" + 
				"Registration number KA-01-HH-1234 with Slot Number 1 is free with Charge 30\n" + 
				"Registration number KA-01-BB-0001 with Slot Number 3 is free with Charge 50\n" + 
				"Registration number DL-12-AA-9999 not found\n" + 
				"Allocated slot number: 1\n" + 
				"Allocated slot number: 3\n" + 
				"Sorry, parking lot is full\n" + 
				"Slot No.    Registration No.\n" + 
				"1           KA-09-HH-0987\n" + 
				"2           KA-01-HH-9999\n" + 
				"3           CA-09-IO-1111\n" + 
				"4           KA-01-HH-7777\n" + 
				"5           KA-01-HH-2701\n" + 
				"6           KA-01-P-333\n";
		
		try {
			outContent.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(expectedOutput, outContent.toString());
	}
}
