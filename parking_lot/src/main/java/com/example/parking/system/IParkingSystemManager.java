package com.example.parking.system;

import com.example.parking.model.IParkingLotState;
import com.example.parking.model.IVehicle;

public interface IParkingSystemManager {
	
	public boolean park(IVehicle vehicle, IParkingLotState parkingSlotState) ;
	
	public boolean unpark(IVehicle vehicle, IParkingLotState parkingSlotState, int parkingHours) ;
	
	public String printLevelOneStatus(IParkingLotState parkingSlotState) ;

	public IParkingLotState getParkingLotState() ;
	
	public void setParkingLotState(IParkingLotState parkingLotState) ;

}
