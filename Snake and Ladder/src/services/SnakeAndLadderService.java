package src.services;

import src.models.*;

import java.util.*;

public class SnakeAndLadderService {
	private SnakeAndLadderBoard snakeAndLadderBoard;
	private int initialNumberOfPlayers;
	private Queue<Player> players; // Comment: Keeping players in game service as they are specifc to this game and not the board. Keeping pieces in the board instead.
	private boolean isGameCompleted;

	private int noOfDices; // Optional Rule 1
	private boolean shouldGameContinueTillLastPlayer; // Optional Rule 3
	private boolean shouldAllowMultipleDiceRollOnSix; // Optional Rule 4

	private static final int DEFUALT_BOARD_SIZE = 100; // The board will have 100 cells numbered from 1 to 100
	private static final int DEFAULT_NO_OF_DICES = 1;

	public SnakeAndLadderService(int boardSize) {
		snakeAndLadderBoard = new SnakeAndLadderBoard(boardSize);
		this.noOfDices = SnakeAndLadderService.DEFAULT_NO_OF_DICES;
	}

	public SnakeAndLadderService() {
		this(SnakeAndLadderService.DEFUALT_BOARD_SIZE);
	}

	/**
	 * Settes for making the game more extensible
	 */
	public void setNoOfDices(int noOfDices) {
		this.noOfDices = noOfDices;
	}

	public void setShouldGameContinueTillLastPlayer(boolean shouldGameContinueTillLastPlayer) {
		this.shouldGameContinueTillLastPlayer = shouldGameContinueTillLastPlayer;
	}

	public void setShouldAllowMultipleDiceRollOnSix(boolean shouldAllowMultipleDiceRollOnSix) {
		this.shouldAllowMultipleDiceRollOnSix = shouldAllowMultipleDiceRollOnSix;
	}

	/**
	 * Initialize board
	 */
	public void setPlayers(List<Player> players) {
		this.players = new LinkedList<Player>();
		this.initialNumberOfPlayers = players.size();
		Map<String, Integer> playerPieces = new HashMap<String, Integer>();
		for (Player player : players) {
			this.players.add(player);
			playerPieces.put(player.getId(), 0); // Each player has a piece which is initially kept outside the board (i.e., position 0)
		}
		snakeAndLadderBoard.setPlayerPieces(playerPieces);
	}

	public void setSnakes(List<Snake> snakes) {
		snakeAndLadderBoard.setSnakes(snakes); // Add snakes to the board
	}

	public void setLadders(List<Ladder> ladders) {
		snakeAndLadderBoard.setLadders(ladders); // Add ladders to the board
	}

	/**
	 * Core business logic for the game
	 */
	private int getNewPostionAfterGoingThroughSnakesAndLadders(int position) {
		int newPosition = position;

		do {
			position = newPosition;
			for (Snake snake : snakeAndLadderBoard.getSnakes()) {
				if (snake.getStart() == position) {
					newPosition = snake.getEnd(); // Whenever a piece ends up at a position with the head of the snake, the piece should go down to the position of the tail of that snake
				}
			}

			for (Ladder ladder : snakeAndLadderBoard.getLadders()) {
				if (ladder.getStart() == position) {
					newPosition = ladder.getEnd(); // Whenever a piece ends up at a position with the start of the ladder, the piece should go up to the position of the end of that ladder
				}
			}
		} while (position != newPosition); // There could be another snake/ladder at the tail of the snake or the end position of the ladder, so the piece should go up/down accordingly
		return newPosition;
	}

	private void movePlayer(Player player, int positions) {
		int oldPosition = snakeAndLadderBoard.getPlayerPieces().get(player.getId());
		int newPosition = oldPosition + positions; // Based on the dice value, the player moves their piece forward that number of cells
		int boardSize = snakeAndLadderBoard.getSize();

		// Can modify this logic to handle side case when there are multiple dices (Optional requirements)
		if (newPosition > boardSize) {
			newPosition = oldPosition; // After a dice roll, if a piece is supposed to move outside position 100, it does not move
		} else {
			newPosition = getNewPostionAfterGoingThroughSnakesAndLadders(newPosition);
		}
		snakeAndLadderBoard.getPlayerPieces().put(player.getId(), newPosition);
		System.out.println(player.getName() + " rolled a " + positions + " and moved from " + oldPosition + " to " + newPosition);
	}

	private int getTotalValueAfterDiceRolls() {
		// Can use noOfDices and shouldAllowMultipleDiceRollOnSix here to get the total value (Optional requirements)
		return DiceService.roll();
	}

	private boolean hasPlayerWon(Player player) {
		// Can change the logic a bit to handle special cases when there are more than one dice (Optional requirements)
		int playerPosition = snakeAndLadderBoard.getPlayerPieces().get(player.getId());
		int winningPosition = snakeAndLadderBoard.getSize();
		return playerPosition == winningPosition;
	}

	private boolean isGameCompleted() {
		// Can use shouldGameContinueTillLastPlayer to change the logic of determining if game is completed (Optional requirements)
		int currentNumberOfPlayer = players.size();
		return currentNumberOfPlayer < initialNumberOfPlayers;
	}

	public void startGame() {
		while (!isGameCompleted()) {
			int totalDiceValue = getTotalValueAfterDiceRolls(); // Each player rolls the dice when their turn comes
			Player currentPlayer = players.poll();
			movePlayer(currentPlayer, totalDiceValue);
			if (hasPlayerWon(currentPlayer)) {
				System.out.println(currentPlayer.getName() + " wins the game");
				snakeAndLadderBoard.getPlayerPieces().remove(currentPlayer.getId());
			} else {
				players.add(currentPlayer);
			}
		}
	}
}