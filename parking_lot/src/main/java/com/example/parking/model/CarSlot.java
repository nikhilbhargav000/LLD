package com.example.parking.model;

public class CarSlot implements ISlot {

	private int slotNo;
	private boolean empty;
	private String parkedRegisterationNo;
	
	public CarSlot(int slotNo, boolean empty) {
		this.slotNo = slotNo;
		this.empty = empty;
	}

	public int getSlotNo() {
		return slotNo;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public String getParkedRegisterationNo() {
		return parkedRegisterationNo;
	}

	public void setParkedRegisterationNo(String parkedRegisterationNo) {
		this.parkedRegisterationNo = parkedRegisterationNo;
	}
	
}
