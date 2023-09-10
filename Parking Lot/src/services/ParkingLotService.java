package src.services;

import src.models.Vehicle;
import src.models.VehicleType;
import src.models.ParkingSlot;
import src.models.ParkingLot;

import java.util.List;
import java.util.ArrayList;
import java.lang.*;

public class ParkingLotService {
	private ParkingLot parkingLot;

	public ParkingLotService(String parkingLotId, int noOfFloors, int noOfSlotsPerFloor) {
		parkingLot = new ParkingLot(parkingLotId, noOfFloors, noOfSlotsPerFloor);
		System.out.println("Created parking lot with " + noOfFloors + " floors and " + noOfSlotsPerFloor + " slots per floor");
	}

	private String getTicketId(String parkingLotId, int floorNo, int slotNo) {
		return parkingLotId + "_" + floorNo + "_" + slotNo;
	}

	private void parkVehicleInSlot(ParkingLot parkingLot, Vehicle vehicle, int noOfFloors, int startingSlot, int endSlot) {
		for (int f = 1; f <= noOfFloors; f++) {
			for (int s = startingSlot; s <= endSlot; s++) {
				ParkingSlot parkingSlot = parkingLot.getParkingSlot(f, s);
				if (parkingSlot.getIsFilled() == false) {
					String ticketId = getTicketId(parkingLot.getParkingLotId(), f, s);
					parkingSlot.setIsFilled(true);
					parkingSlot.setVehicle(ticketId, vehicle);
					System.out.println("Parked vehicle. Ticket ID: " + ticketId);
					return;
				}
			}
		}
		System.out.println("Parking Lot Full");
	}

	public void parkVehicle(VehicleType vehicleType, String registrationNo, String vehicleColor) {
		int noOfFloors = parkingLot.getNoOfFloors();
		int noOfSlotsPerFloor = parkingLot.getNoOfSlotsPerFloor();
		Vehicle vehicle = new Vehicle(vehicleType, registrationNo, vehicleColor);

		switch (vehicleType) {
		case TRUCK:
			parkVehicleInSlot(parkingLot, vehicle, noOfFloors, 1, 1);
			break;
		case BIKE:
			parkVehicleInSlot(parkingLot, vehicle, noOfFloors, 2, 3);
			break;
		case CAR:
			parkVehicleInSlot(parkingLot, vehicle, noOfFloors, 4, noOfSlotsPerFloor);
			break;
		default:
			System.out.println("Unsupported Vehicle Type");
		}
	}

	public void unparkVehicle(String ticketId) {
		String[] parts = ticketId.split("_");
		String parkingLotId = parts[0];
		int floorNo = Integer.parseInt(parts[1]);
		int slotNo = Integer.parseInt(parts[2]);

		int noOfFloors = parkingLot.getNoOfFloors();
		int noOfSlotsPerFloor = parkingLot.getNoOfSlotsPerFloor();

		if (parkingLotId.equals(parkingLot.getParkingLotId()) && floorNo <= noOfFloors && slotNo <= noOfSlotsPerFloor) {
			ParkingSlot parkingSlot = parkingLot.getParkingSlot(floorNo, slotNo);
			if (parkingSlot.getIsFilled() && parkingSlot.getTicketId().equals(ticketId)) {
				Vehicle vehicle = parkingSlot.getVehicle();
				parkingSlot.setIsFilled(false);
				parkingSlot.setVehicle(null, null);
				System.out.println("Unparked vehicle with Registration Number: " + vehicle.getRegNo() + " and Color: " + vehicle.getColor());
			} else {
				System.out.println("Invalid Ticket");
			}
		} else {
			System.out.println("Invalid Ticket");
		}
	}

	public void display(String displayType, VehicleType vehicleType) {
		int noOfFloors = parkingLot.getNoOfFloors();
		int noOfSlotsPerFloor = parkingLot.getNoOfSlotsPerFloor();

		for (int f = 1; f <= noOfFloors; f++) {
			int freeSlotsCount = 0;
			List<Integer> freeSlots = new ArrayList<Integer>();
			List<Integer> occupiedSlots = new ArrayList<Integer>();

			for (int s = 1; s <= noOfSlotsPerFloor; s++) {
				ParkingSlot parkingSlot = parkingLot.getParkingSlot(f, s);
				if (parkingSlot.getSupportedVehicle() == vehicleType) {
					if (parkingSlot.getIsFilled()) {
						occupiedSlots.add(s);
					} else {
						freeSlotsCount++;
						freeSlots.add(s);
					}
				}
			}

			switch (displayType) {
			case "free_count":
				System.out.println("No. of free slots for " + vehicleType + " on Floor " + f + ": " + freeSlotsCount);
				break;
			case "free_slots":
				System.out.print("Free slots for " + vehicleType + " on Floor " + f + ": ");
				for (int i = 0; i < freeSlots.size(); i++) {
					if (i == 0)
						System.out.print(freeSlots.get(i));
					else
						System.out.print(", " + freeSlots.get(i));
				}
				System.out.println();
				break;
			case "occupied_slots":
				System.out.print("Occupied slots for " + vehicleType + " on Floor " + f + ": ");
				for (int i = 0; i < occupiedSlots.size(); i++) {
					if (i == 0)
						System.out.print(occupiedSlots.get(i));
					else
						System.out.print(", " + occupiedSlots.get(i));
				}
				System.out.println();
				break;
			default:
				System.out.println("Unsupported display type.");
			}
		}
	}
}