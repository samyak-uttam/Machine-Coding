package src;

import src.models.Vehicle;
import src.models.VehicleType;
import src.services.ParkingLotService;

import java.util.Scanner;
import java.io.*;
import java.lang.*;

public class Driver {
	public static void main(String[] args) throws FileNotFoundException {
		try {
			Scanner scanner = new Scanner(new File("../input.txt"));
			ParkingLotService parkingLotService = null;

			while (scanner.hasNextLine()) {
				String command = scanner.nextLine();

				if (command.equals("exit")) {
					break;
				}

				String[] commands = command.split(" ");
				switch (commands[0]) {
				case "create_parking_lot":
					int noOfFloors = Integer.parseInt(commands[2]);
					int noOfSlotsPerFloor = Integer.parseInt(commands[3]);
					parkingLotService = new ParkingLotService(commands[1], noOfFloors, noOfSlotsPerFloor);
					break;
				case "park_vehicle":
					parkingLotService.parkVehicle(VehicleType.valueOf(commands[1]), commands[2], commands[3]);
					break;
				case "unpark_vehicle":
					parkingLotService.unparkVehicle(commands[1]);
					break;
				case "display":
					parkingLotService.display(commands[1], VehicleType.valueOf(commands[2]));
					break;
				}
			}

		} catch (FileNotFoundException ex) {
			System.out.println(ex);
		}
	}
}