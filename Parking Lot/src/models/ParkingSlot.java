package src.models;

public class ParkingSlot {
	private int floorNo;
	private int slotNo;
	private String ticketId;
	private VehicleType supportedVehicle;
	private Vehicle vehicle;
	private boolean isFilled;

	public ParkingSlot(int floorNo, int slotNo) {
		this.floorNo = floorNo;
		this.slotNo = slotNo;

		if (slotNo == 1) {
			supportedVehicle = VehicleType.TRUCK;
		} else if (slotNo <= 3) {
			supportedVehicle = VehicleType.BIKE;
		} else {
			supportedVehicle = VehicleType.CAR;
		}
	}

	public int getFloorNo() {
		return floorNo;
	}

	public int getSlotNo() {
		return slotNo;
	}

	public VehicleType getSupportedVehicle() {
		return supportedVehicle;
	}

	public String getTicketId() {
		return ticketId;
	}

	public boolean getIsFilled() {
		return isFilled;
	}

	public void setIsFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(String ticketId, Vehicle vehicle) {
		this.ticketId = ticketId;
		this.vehicle = vehicle;
	}
}