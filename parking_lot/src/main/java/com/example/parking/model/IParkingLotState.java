package com.example.parking.model;

import java.util.List;
import java.util.Map;

public interface IParkingLotState {
	
	public List<ParkingLevel> getParkingLevels() ;
	
	public Map<String, IParkLocation> getVehicleLocationMap() ;

	public IParkLocation  getCloestLocationToEntry() ;
	
	public void setCloestLocationToEntry(IParkLocation cloestLocationToEntry) ;
	
}
