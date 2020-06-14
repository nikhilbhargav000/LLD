package com.example.parking.model;

public interface IParkLocation {
	
	public int getParkLevel() ;
	
	public int getSlotNo() ;
	
	public int compareTo(IParkLocation otherLocation) ;
	
}
