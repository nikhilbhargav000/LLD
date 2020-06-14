package com.example.parking.executor;

import com.example.parking.command.Command;
import com.example.parking.command.CreateOneCarParkingSlotCommand;
import com.example.parking.command.ParkCarCommand;
import com.example.parking.command.ParkingCommandInvoker;
import com.example.parking.command.ParkingLotCommand;
import com.example.parking.command.ParkingStatusLevelOneCommand;
import com.example.parking.command.UnparkCarCommand;
import com.example.parking.exception.ParkingLotException;
import com.example.parking.model.CarVehicle;
import com.example.parking.system.CommonUtil;
import com.example.parking.system.IParkingSystemManager;
import com.example.parking.system.ParkingSystemManager;

public class ParkingCommandExecutor implements IParkingCommandExecutor {
	
	public void execute(String command) {
		String[] commandArr = command.split(" ");
		
		ParkingLotCommand commandType = getParkingLotCommand(commandArr);
		if (commandType == null) {
			System.out.println("Invalid Command : " + command);
			return;
		}
		
		IParkingSystemManager parkingManager = ParkingSystemManager.getInstance();
		switch (commandType) {
			case CREATE:
				createCommand(commandArr, parkingManager, command);
				break;
			case PARK:
				parkCommand(commandArr, parkingManager, command);
				break;
			case UNPARK:
				unparkCommand(commandArr, parkingManager, command);
				break;
			case STATUS:
				statusCommand(commandArr, parkingManager, command);
				break;
			default:
				System.out.println("Invalid Command : " + command);
		}
		
		return;
	}
	
	//////////////////////////////////////////////////
	/////////// Privates
	//////////////////////////////////////////////////
	
	private void createCommand(String[] commandArr, IParkingSystemManager parkingManager, String command) {
		if (commandArr.length < 2) 
			throw new ParkingLotException("Invalid Command : " + command);
		
		Integer parkingSlotsCount = CommonUtil.getInteger(commandArr[1]);
		if (parkingSlotsCount == null) 
			throw new ParkingLotException("Invalid Command : " + command);
		
		Command createCommand = new CreateOneCarParkingSlotCommand(parkingSlotsCount, parkingManager);
		ParkingCommandInvoker invoker = new ParkingCommandInvoker(createCommand);
		invoker.execute();
		
		return;
	}
	
	private void parkCommand(String[] commandArr, IParkingSystemManager parkingManager, String command) {
		if (commandArr.length < 2 || !CommonUtil.isNotEmpty(commandArr[1])) 
			throw new ParkingLotException("Invalid Command : " + command);
		
		String registerationNo = commandArr[1];
		CarVehicle car = new CarVehicle(registerationNo);
		
		Command parkCommand = new ParkCarCommand(car, parkingManager);
		ParkingCommandInvoker invoker = new ParkingCommandInvoker(parkCommand);
		invoker.execute();
		
		return;
	}
	
	private void unparkCommand(String[] commandArr, IParkingSystemManager parkingManager, String command) {
		if (commandArr.length < 3 || !CommonUtil.isNotEmpty(commandArr[1])) 
			throw new ParkingLotException("Invalid Command : " + command);
		
		String registerationNo = commandArr[1];
		Integer parkingHours = CommonUtil.getInteger(commandArr[2]);
		if (parkingHours == null)
			throw new ParkingLotException("Invalid Command : " + command);
		
		CarVehicle car = new CarVehicle(registerationNo);
		
		Command unparkCommand = new UnparkCarCommand(parkingHours, car, parkingManager);
		ParkingCommandInvoker invoker = new ParkingCommandInvoker(unparkCommand);
		invoker.execute();
		
		return;
	}
	
	private void statusCommand(String[] commandArr, IParkingSystemManager parkingManager, String command) {
		
		Command statusCommand = new ParkingStatusLevelOneCommand(parkingManager);
		ParkingCommandInvoker invoker = new ParkingCommandInvoker(statusCommand);
		invoker.execute();
		
		return;
	}
	
	public ParkingLotCommand getParkingLotCommand(String[] commandArr) {
		ParkingLotCommand commandType = null;
		
		if (commandArr.length < 1)
			return commandType;
		commandType = ParkingLotCommand.getCommandByValue(commandArr[0]);
		return commandType;
	}
	
}
