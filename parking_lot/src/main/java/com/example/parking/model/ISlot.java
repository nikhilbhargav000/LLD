package com.example.parking.model;

public interface ISlot {
	
	public int getSlotNo() ;
	
	public boolean isEmpty() ;
	
	public void setEmpty(boolean empty) ;
	
	public void setParkedRegisterationNo(String parkedRegisterationNo) ;
	
	public String getParkedRegisterationNo() ;
	
}
