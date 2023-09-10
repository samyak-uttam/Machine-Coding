package src;

import src.models.Player;
import src.models.CellType;
import src.services.TicTacToeService;

import java.util.Scanner;
import java.io.*;

public class Driver {
	public static void main(String[] args) throws FileNotFoundException {
		try {
			TicTacToeService ticTacToeService = new TicTacToeService();

			Scanner scanner = new Scanner(new File("../input.txt"));

			for (int u = 1; u <= 2; u++) {
				String cellType = scanner.next();
				String userName = scanner.next();

				ticTacToeService.addPlayer(new Player("user" + u, userName, CellType.valueOfLabel(cellType)));
			}
			scanner.nextLine();

			while (scanner.hasNextLine()) {
				String command = scanner.nextLine();

				if (command.equals("exit")) {
					break;
				}

				String[] commands = command.split(" ");
				ticTacToeService.move(Integer.parseInt(commands[0]), Integer.parseInt(commands[1]));

			}
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
		}
	}
}