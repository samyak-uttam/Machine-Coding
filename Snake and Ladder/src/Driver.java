package src;

import src.models.Ladder;
import src.models.Player;
import src.models.Snake;

import src.services.SnakeAndLadderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.*;

public class Driver {
	public static void main(String[] args) throws FileNotFoundException {
		try {
			Scanner scanner = new Scanner(new File("../input.txt"));

			int noOfSnakes = scanner.nextInt();
			List<Snake> snakes = new ArrayList<Snake>();
			for (int i = 0; i < noOfSnakes; i++) {
				snakes.add(new Snake(scanner.nextInt(), scanner.nextInt()));
			}

			int noOfLadders = scanner.nextInt();
			List<Ladder> ladders = new ArrayList<Ladder>();
			for (int i = 0; i < noOfLadders; i++) {
				ladders.add(new Ladder(scanner.nextInt(), scanner.nextInt()));
			}

			int noOfPlayers = scanner.nextInt();
			List<Player> players = new ArrayList<Player>();
			for (int i = 0; i < noOfPlayers; i++) {
				players.add(new Player(scanner.next()));
			}

			SnakeAndLadderService snakeAndLadderService = new SnakeAndLadderService();
			snakeAndLadderService.setPlayers(players);
			snakeAndLadderService.setSnakes(snakes);
			snakeAndLadderService.setLadders(ladders);

			snakeAndLadderService.startGame();
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
		}
	}
}