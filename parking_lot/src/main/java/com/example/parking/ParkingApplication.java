package com.example.parking;

import java.util.List;

import com.example.parking.exception.ParkingLotException;
import com.example.parking.executor.IParkingCommandExecutor;
import com.example.parking.executor.ParkingCommandExecutor;
import com.example.parking.parser.IInputFileToCommandParser;
import com.example.parking.parser.InputFileToCommandsParser;

public class ParkingApplication 
{
    public static void main( String[] args ) {
    	ParkingApplication parkingApp = new ParkingApplication();
    	if (args.length == 0)	
    		return;
    	parkingApp.driverFunction(args[0]);
    }
    
    public void driverFunction(String inputFilePath) {
    	
    	IInputFileToCommandParser fileParser = new InputFileToCommandsParser(inputFilePath);
    	
    	List<String> commands = null;
    	try {
			commands = fileParser.parseToCommandList();
		} catch (Exception e) {
			System.out.println("Error while reading input file : " + inputFilePath);
		}
    	executeCommands(commands);
    	
    	return;
    }
    
    public void executeCommands(List<String> commands) {

    	// Execute each command
    	IParkingCommandExecutor executor = new ParkingCommandExecutor();
    	for (String command : commands) {
    		
    		try {
    			executor.execute(command);
    		} catch (ParkingLotException e) {
    			System.out.println(e.getMessage());
    		}
    	}
    	
    	return;
    }
    
}
