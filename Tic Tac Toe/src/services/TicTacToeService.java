package src.services;

import src.models.CellType;
import src.models.Player;
import src.models.TicTacToeBoard;

import java.util.List;
import java.util.ArrayList;

public class TicTacToeService {
	private int turn;
	private boolean isGameCompleted;
	private List<Player> players;
	private TicTacToeBoard ticTacToeBoard;

	private static final int DEFAULT_BOARD_SIZE = 3;

	public TicTacToeService(int boardSize) {
		turn = 0;
		isGameCompleted = false;
		players = new ArrayList<Player>();
		ticTacToeBoard = new TicTacToeBoard(boardSize);
		printBoard();
	}

	public TicTacToeService() {
		this(TicTacToeService.DEFAULT_BOARD_SIZE);
	}

	public void addPlayer(Player player) {
		players.add(player);
	}

	public void move(int rowIndex, int colIndex) {
		if (isGameCompleted == false) {
			Player currentPlayer = players.get(turn);
			boolean isValidMove = ticTacToeBoard.setCellValue(rowIndex, colIndex, currentPlayer.getCellType());
			validateAfterMoving(isValidMove, currentPlayer);
		}
	}

	private void validateAfterMoving(boolean isValidMove, Player currentPlayer) {
		if (isValidMove) {
			printBoard();
			if (hasPlayerWon(currentPlayer)) {
				isGameCompleted = true;
				System.out.println(currentPlayer.getName() + " won the game");
			} else if (checkGameOver()) {
				isGameCompleted = true;
				System.out.println("Game Over");
			}
			turn = (turn + 1) % players.size();
		} else {
			System.out.println("Invalid Move");
		}
	}

	private boolean checkGameOver() {
		int boardSize = ticTacToeBoard.getBoardSize();
		boolean isGameOver = true;
		for (int r = 1; r <= boardSize && isGameOver; r++) {
			for (int c = 1; c <= boardSize; c++) {
				if (ticTacToeBoard.getCellValue(r, c) == CellType.START) {
					isGameOver = false;
				}
			}
		}
		return isGameOver;
	}

	private boolean hasPlayerWon(Player player) {
		String playerPiece = player.getCellType().label;
		return checkRows(playerPiece) || checkColumns(playerPiece) || checkDiagonals(playerPiece);
	}

	private boolean checkRows(String playerPiece) {
		int boardSize = ticTacToeBoard.getBoardSize();
		boolean hasWon = false;
		for (int r = 1; r <= boardSize && !hasWon; r++) {
			boolean currentRow = true;
			for (int c = 1; c <= boardSize && currentRow; c++) {
				if (ticTacToeBoard.getCellValue(r, c).label != playerPiece) {
					currentRow = false;
				}
			}
			hasWon = hasWon || currentRow;
		}
		return hasWon;
	}

	private boolean checkColumns(String playerPiece) {
		int boardSize = ticTacToeBoard.getBoardSize();
		boolean hasWon = false;
		for (int c = 1; c <= boardSize && !hasWon; c++) {
			boolean currentCol = true;
			for (int r = 1; r <= boardSize && currentCol; r++) {
				if (ticTacToeBoard.getCellValue(r, c).label != playerPiece) {
					currentCol = false;
				}
			}
			hasWon = hasWon || currentCol;
		}
		return hasWon;
	}

	private boolean checkDiagonals(String playerPiece) {
		int boardSize = ticTacToeBoard.getBoardSize();
		boolean primaryDiag = true, secondaryDiag = true;
		for (int r = 1; r <= boardSize; r++) {
			for (int c = 1; c <= boardSize; c++) {
				if (r == c && ticTacToeBoard.getCellValue(r, c).label != playerPiece) {
					primaryDiag = false;
				} else if (r + c == boardSize + 1 && ticTacToeBoard.getCellValue(r, c).label != playerPiece) {
					secondaryDiag = false;
				}
			}
		}
		return primaryDiag || secondaryDiag;
	}

	private void printBoard() {
		int boardSize = ticTacToeBoard.getBoardSize();
		for (int r = 1; r <= boardSize; r++) {
			for (int c = 1; c <= boardSize; c++) {
				System.out.print(ticTacToeBoard.getCellValue(r, c).label + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}