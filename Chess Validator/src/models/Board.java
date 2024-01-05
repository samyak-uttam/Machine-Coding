package src.models;

import src.models.piece.Piece;
import src.models.piece.Rook;
import src.models.piece.Knight;
import src.models.piece.Bishop;
import src.models.piece.Queen;
import src.models.piece.King;
import src.models.piece.Pawn;

public class Board {
	private int BOARD_SIZE = 8;
	private Spot[][] board;

	public Board() {
		board = new Spot[BOARD_SIZE][BOARD_SIZE];
		this.resetBoard();
	}

	public int getBoardSize() {
		return BOARD_SIZE;
	}

	public void resetBoard() {
		// set black pieces
		board[0][0] = new Spot(0, 0, new Rook(false));
		board[0][1] = new Spot(0, 1, new Knight(false));
		board[0][2] = new Spot(0, 2, new Bishop(false));
		board[0][3] = new Spot(0, 3, new Queen(false));
		board[0][4] = new Spot(0, 4, new King(false));
		board[0][5] = new Spot(0, 5, new Bishop(false));
		board[0][6] = new Spot(0, 6, new Knight(false));
		board[0][7] = new Spot(0, 7, new Rook(false));

		for (int c = 0; c < BOARD_SIZE; c++) {
			board[1][c] = new Spot(1, c, new Pawn(false));
		}

		// set white pieces
		board[7][0] = new Spot(7, 0, new Rook(true));
		board[7][1] = new Spot(7, 1, new Knight(true));
		board[7][2] = new Spot(7, 2, new Bishop(true));
		board[7][3] = new Spot(7, 3, new Queen(true));
		board[7][4] = new Spot(7, 4, new King(true));
		board[7][5] = new Spot(7, 5, new Bishop(true));
		board[7][6] = new Spot(7, 6, new Knight(true));
		board[7][7] = new Spot(7, 7, new Rook(true));

		for (int c = 0; c < BOARD_SIZE; c++) {
			board[6][c] = new Spot(6, c, new Pawn(true));
		}

		// set the empty peices
		for (int r = 2; r < 6; r++) {
			for (int c = 0; c < BOARD_SIZE; c++)
				board[r][c] = new Spot(r, c, null);
		}
	}

	public Spot getSpot(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			System.out.println("Index out of bound");
			return null;
		}
		return board[x][y];
	}

	public void setPiece(int x, int y, Piece piece) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			System.out.println("Index out of bound");
			return;
		}
		board[x][y].setPiece(piece);
	}
}