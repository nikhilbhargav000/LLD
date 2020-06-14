package com.example.parking.model;

public class CarVehicle implements IVehicle {

	private String color;
	private String registerationNo;

	public CarVehicle(String registerationNo) {
		this.registerationNo = registerationNo;
	}
	
	public String getColor() {
		return color;
	}
	public String getRegisterationNo() {
		return registerationNo;
	}
	
}
