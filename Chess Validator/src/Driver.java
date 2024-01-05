package src;

import src.services.ChessValidatorService;

import java.util.Scanner;
import java.io.*;

public class Driver {
	public static void main(String[] args) throws FileNotFoundException {
		try {
			Scanner scanner = new Scanner(new File("../input.txt"));
			ChessValidatorService chessValidatorService = new ChessValidatorService();

			while (scanner.hasNextLine()) {
				String command = scanner.nextLine();

				if (command.equals("exit")) {
					break;
				}

				String[] positions = command.split(" ");
				chessValidatorService.playTurn(positions[0], positions[1]);
			}
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
		}
	}
}