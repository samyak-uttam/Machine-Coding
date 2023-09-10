package src.models;

public class ParkingLot {
	private String parkingLotId;
	private int noOfFloors;
	private int noOfSlotsPerFloor;
	private ParkingSlot[][] floors;

	public ParkingLot(String parkingLotId, int noOfFloors, int noOfSlotsPerFloor) {
		this.parkingLotId = parkingLotId;
		this.noOfFloors = noOfFloors;
		this.noOfSlotsPerFloor = noOfSlotsPerFloor;
		floors = new ParkingSlot[noOfFloors + 1][noOfSlotsPerFloor + 1];
		initializeParkingLot();
	}

	private void initializeParkingLot() {
		for (int f = 1; f <= noOfFloors; f++) {
			for (int s = 1; s <= noOfSlotsPerFloor; s++) {
				floors[f][s] = new ParkingSlot(f, s);
			}
		}
	}

	public String getParkingLotId() {
		return parkingLotId;
	}

	public int getNoOfFloors() {
		return noOfFloors;
	}

	public int getNoOfSlotsPerFloor() {
		return noOfSlotsPerFloor;
	}

	public ParkingSlot getParkingSlot(int floorNo, int slotNo) {
		return floors[floorNo][slotNo];
	}
}