package src.models;

public class Vehicle {
	private VehicleType vehicleType;
	private String regNo;
	private String color;

	public Vehicle(VehicleType vehicleType, String regNo, String color) {
		this.vehicleType = vehicleType;
		this.regNo = regNo;
		this.color = color;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public String getRegNo() {
		return regNo;
	}

	public String getColor() {
		return color;
	}
}