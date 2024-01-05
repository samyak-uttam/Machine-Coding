package src.services;

import src.models.Board;
import src.models.Spot;
import src.models.piece.Piece;
import src.models.piece.PieceType;

public class ChessValidatorService {
	private boolean turn;
	private Board board;

	public ChessValidatorService() {
		turn = true;
		board = new Board();
		printBoard();
	}

	private Spot getSpotFromPosition(String position) {
		int c = position.charAt(0) - 'a';
		int r = board.getBoardSize() - (position.charAt(1) - '0');
		return board.getSpot(r, c);
	}

	private void printBoard() {
		for (int r = 0; r < board.getBoardSize(); r++) {
			for (int c = 0; c < board.getBoardSize(); c++) {
				Piece curPiece = board.getSpot(r, c).getPiece();
				if (curPiece == null)
					System.out.print("-- ");
				else {
					if (curPiece.getIsWhite() == true)
						System.out.print("W" + curPiece.getPieceType().label + " ");
					else
						System.out.print("B" + curPiece.getPieceType().label + " ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	private void movePiece(Spot start, Spot end) {
		board.setPiece(end.getX(), end.getY(), start.getPiece());
		board.setPiece(start.getX(), start.getY(), null);
	}

	public void playTurn(String startPos, String endPos) {
		Spot startSpot = getSpotFromPosition(startPos);
		Spot endSpot = getSpotFromPosition(endPos);

		if (!MoveValidator.validateMove(board, startSpot, endSpot, turn)) {
			System.out.println("Invalid Move");
			return;
		}

		movePiece(startSpot, endSpot);

		printBoard();
		turn = !turn;
	}
}