package src.models;

public class Board {
	private int BOARD_SIZE = 8;
	private Spot[][] board;

	public Board() {
		this.resetBoard();
	}

	public int getBoardSize() {
		return BOARD_SIZE;
	}

	public void resetBoard() {
		board = new Spot[BOARD_SIZE][BOARD_SIZE];
	}

	public Spot getSpot(int x, int y) {
		return board[x][y];
	}
}