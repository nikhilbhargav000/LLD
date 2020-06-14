package com.example.parking.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotState implements IParkingLotState {
	
	private List<ParkingLevel> parkingLevels;
	private Map<String, IParkLocation> vehicleLocationMap;
	private IParkLocation  cloestLocationToEntry;
	
	public ParkingLotState() {
		this.parkingLevels = new ArrayList<ParkingLevel>();
		this.vehicleLocationMap = new HashMap<String, IParkLocation>();
		this.cloestLocationToEntry = new ParkLocation(1, 1);
	}

	public List<ParkingLevel> getParkingLevels() {
		return parkingLevels;
	}
	
	public Map<String, IParkLocation> getVehicleLocationMap() {
		return vehicleLocationMap;
	}

	public IParkLocation  getCloestLocationToEntry() {
		return cloestLocationToEntry;
	}

	public void setCloestLocationToEntry(IParkLocation  cloestLocationToEntry) {
		this.cloestLocationToEntry = cloestLocationToEntry;
	}
	
}
