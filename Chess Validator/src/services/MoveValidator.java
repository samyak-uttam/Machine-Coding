package src.services;

import src.models.Board;
import src.models.Spot;
import src.models.piece.Piece;

public class MoveValidator {

	public static boolean validateMove(Board board, Spot start, Spot end, boolean turn) {
		Piece startPiece = start.getPiece();
		Piece endPiece = end.getPiece();
		if (startPiece == null || startPiece.getIsWhite() != turn)
			return false;
		if (endPiece != null && endPiece.getIsWhite() == turn)
			return false;
		if (!checkBoundaries(board, start) || !checkBoundaries(board, end) || !checkSpot(start, end))
			return false;
		if (!startPiece.canMove(board, start, end) || !checkMiddlePieces(board, start, end))
			return false;
		return true;
	}

	private static boolean checkBoundaries(Board board, Spot spot) {
		int board_size = board.getBoardSize();
		int x = spot.getX(), y = spot.getY();
		return (x >= 0 && x < board_size) && (y >= 0 && y < board_size);
	}

	private static boolean checkSpot(Spot start, Spot end) {
		return !(start.getX() == end.getX() && start.getY() == end.getY());
	}

	private static boolean checkMiddlePieces(Board board, Spot start, Spot end) {
		int x1 = start.getX(), y1 = start.getY();
		int x2 = end.getX(), y2 = end.getY();

		if (x1 == x2 || y1 == y2) {
			if (x1 == x2) {
				int j = y1 < y2 ? y1 + 1 : y2 + 1;
				for (; j < Math.max(y1, y2); j++) {
					if (board.getSpot(x2, j).getPiece() != null)
						return false;
				}
			} else {
				int i = x1 < x2 ? x1 + 1 : x2 + 1;
				for (; i < Math.max(x1, x2); i++) {
					if (board.getSpot(i, y2).getPiece() != null)
						return false;
				}
			}
		} else if (Math.abs(x2 - x1) == Math.abs(y2 - y1)) {
			if ((x1 < x2 && y1 < y2) || (x1 > x2 && y1 > y2)) {
				int i = x1 < x2 ? x1 + 1 : x2 + 1;
				int j = y1 < y2 ? y1 + 1 : y2 + 1;
				for (; i < Math.max(x1, x2) && j < Math.max(y1, y2); i++, j++) {
					if (board.getSpot(i, j).getPiece() != null)
						return false;
				}
			} else {
				int i = x1 < x2 ? x1 + 1 : x2 + 1;
				int j = y1 > y2 ? y1 - 1 : y2 - 1;
				for (; i < Math.max(x1, x2) && j > Math.min(y1, y2); i++, j--) {
					if (board.getSpot(i, j).getPiece() != null)
						return false;
				}
			}
		}
		return true;
	}
}