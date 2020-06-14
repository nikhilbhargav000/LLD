package com.example.parking.model;

import java.util.ArrayList;
import java.util.List;

public class ParkingLevel implements IParkingLevel {
	
	private List<ISlot> slots;

	public ParkingLevel() {
		this.slots = new ArrayList<ISlot>();
	}

	public List<ISlot> getSlots() {
		return slots;
	}
	
}
