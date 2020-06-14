package com.example.parking.system;

import com.example.parking.exception.ParkingLotException;
import com.example.parking.model.IParkLocation;
import com.example.parking.model.IParkingLevel;
import com.example.parking.model.IParkingLotState;
import com.example.parking.model.ISlot;
import com.example.parking.model.IVehicle;
import com.example.parking.model.ParkLocation;

public class ParkingSystemManager implements IParkingSystemManager {

	/**
	 * Singleton parking system manager
	 */
	private static ParkingSystemManager parkingSystemManager;
	private IParkingLotState parkingLotState;

	private ParkingSystemManager() {

	}

	/**
	 * Get Singleton parking system manager instance
	 */
	public static ParkingSystemManager getInstance() {
		if (parkingSystemManager == null) {
			synchronized (ParkingSystemManager.class) {
				if (parkingSystemManager == null) {
					parkingSystemManager = new ParkingSystemManager();
				}
			}
		}
		return parkingSystemManager;
	}

	/**
	 * Parking implementations
	 */

	public IParkingLotState getParkingLotState() {
		return parkingLotState;
	}

	public void setParkingLotState(IParkingLotState parkingLotState) {
		this.parkingLotState = parkingLotState;
	}

	public boolean park(IVehicle vehicle, IParkingLotState parkingSlotState) {
		boolean vehicleParked = false;

		if (parkingSlotState == null)
			throw new ParkingLotException("No parking lot found");
		synchronized (parkingSlotState) {

			IParkLocation parkLocation = parkingSlotState.getCloestLocationToEntry();
			if (parkLocation == null) {
				System.out.println("Sorry, parking lot is full");
				return vehicleParked;
			}
			
			if (parkingSlotState.getVehicleLocationMap().containsKey(vehicle.getRegisterationNo())) {
				System.out.println("Vehicle with registeration no " + vehicle.getRegisterationNo() + " is already parked");
				return vehicleParked;
			}

			ISlot slot = parkingSlotState.getParkingLevels().get(parkLocation.getParkLevel()).getSlots()
					.get(parkLocation.getSlotNo());
			slot.setEmpty(false);
			slot.setParkedRegisterationNo(vehicle.getRegisterationNo());
			vehicleParked = true;

			// Update
			parkingSlotState.getVehicleLocationMap().put(vehicle.getRegisterationNo(),
					new ParkLocation(parkLocation.getParkLevel(), parkLocation.getSlotNo()));

			System.out.println("Allocated slot number: " + parkLocation.getSlotNo());
			updateNextParkingAfterPark(parkingSlotState, parkLocation);
		}

		return vehicleParked;
	}

	public boolean unpark(IVehicle vehicle, IParkingLotState parkingSlotState, int parkingHours) {
		boolean vehicleUnparked = false;

		if (parkingSlotState == null)
			throw new ParkingLotException("No parking lot found");
		IParkLocation unparkLocation = null;
		synchronized (parkingSlotState) {

			if (!parkingSlotState.getVehicleLocationMap().containsKey(vehicle.getRegisterationNo())) {
				throw new ParkingLotException("Registration number " + vehicle.getRegisterationNo() + " not found");
			}

			unparkLocation = parkingSlotState.getVehicleLocationMap().get(vehicle.getRegisterationNo());
			if (unparkLocation.getParkLevel() >= parkingLotState.getParkingLevels().size()
					|| unparkLocation.getSlotNo() >= parkingLotState.getParkingLevels()
							.get(unparkLocation.getParkLevel()).getSlots().size()) {
				throw new ParkingLotException("Invalid parking location");
			}

			ISlot unparkSlot = parkingLotState.getParkingLevels().get(unparkLocation.getParkLevel()).getSlots()
					.get(unparkLocation.getSlotNo());
			unparkSlot.setEmpty(true);
			unparkSlot.setParkedRegisterationNo(null);
			parkingSlotState.getVehicleLocationMap().remove(vehicle.getRegisterationNo());

			updateNextParkingAfterUnpark(parkingSlotState, unparkLocation);
			vehicleUnparked = true;
		}
		long cost = getParkingCost(parkingHours);

		System.out.println("Registration number " + vehicle.getRegisterationNo() + " with Slot Number "
				+ unparkLocation.getSlotNo() + " is free with Charge " + cost);

		return vehicleUnparked;
	}

	public String printLevelOneStatus(IParkingLotState parkingSlotState) {
		String status = null;
		
		StringBuilder statusBuilder = new StringBuilder();
		
		IParkingLevel parkingLevel = parkingSlotState.getParkingLevels().get(1);
		for (int slotIdx = 1; slotIdx < parkingLevel.getSlots().size() ; slotIdx++) {
			if (slotIdx == 1) {
				statusBuilder.append("Slot No.    Registration No.\n");
			}
			ISlot slot = parkingLevel.getSlots().get(slotIdx);
			if (!slot.isEmpty()) 
				statusBuilder.append(slotIdx + "           " + slot.getParkedRegisterationNo() + "\n");
		}
		
		status = statusBuilder.toString();
		System.out.print(status);
		
		return status;
	}

	//////////////////////////////////////////////////
	////////// Privates
	//////////////////////////////////////////////////

	private void updateNextParkingAfterPark(IParkingLotState parkingSlotState, IParkLocation parkLocation) {

		parkingSlotState.setCloestLocationToEntry(null);

		boolean foundLocation = false;
		for (int parklevelIdx = parkLocation.getParkLevel(); parklevelIdx < parkingSlotState.getParkingLevels()
				.size(); parklevelIdx++) {

			IParkingLevel parkingLevel = parkingSlotState.getParkingLevels().get(parklevelIdx);

			int slotIdx = 1;
			if (parklevelIdx == parkLocation.getParkLevel())
				slotIdx = parkLocation.getSlotNo() + 1;

			for ( ; slotIdx < parkingLevel.getSlots().size(); slotIdx++) {
				if (parkingLevel.getSlots().get(slotIdx).isEmpty()) {
					parkingLotState.setCloestLocationToEntry(new ParkLocation(parklevelIdx, slotIdx));

					foundLocation = true;
					break;
				}
			}

			if (foundLocation)
				break;
		}
	}

	private void updateNextParkingAfterUnpark(IParkingLotState parkingSlotState, IParkLocation unparkLocation) {
		
		if (parkingSlotState.getCloestLocationToEntry() == null) {
			parkingSlotState.setCloestLocationToEntry(unparkLocation);
			return;
		}
		
		if (unparkLocation.compareTo(parkingSlotState.getCloestLocationToEntry()) <= 0) {
			parkingSlotState.setCloestLocationToEntry(unparkLocation);
		}

		return;
	}

	private long getParkingCost(long parkingHours) {
		long cost = 0L;

		cost += 10L; // cost for first two hours
		parkingHours = parkingHours - 2L;

		// Addition hours
		if (parkingHours > 0L) {
			cost += (parkingHours * 10L);
		}

		return cost;
	}

}
