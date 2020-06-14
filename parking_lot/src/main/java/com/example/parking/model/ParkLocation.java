package com.example.parking.model;

public class ParkLocation implements IParkLocation, Comparable<IParkLocation> {

	final private int parkLevel;
	final private int slotNo;
	
	public ParkLocation(int parkLevel, int slotNo) {
		super();
		this.parkLevel = parkLevel;
		this.slotNo = slotNo;
	}

	public int getParkLevel() {
		return parkLevel;
	}

	public int getSlotNo() {
		return slotNo;
	}

	public int compareTo(IParkLocation otherLocation) {
		if (this.getParkLevel() - otherLocation.getParkLevel() == 0)
			return this.getSlotNo() - otherLocation.getSlotNo();
		return this.getParkLevel() - otherLocation.getParkLevel();
	}
}
