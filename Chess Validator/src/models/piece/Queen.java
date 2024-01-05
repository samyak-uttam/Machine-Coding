package src.models.piece;

import src.models.Spot;
import src.models.Board;
import src.models.piece.PieceType;

public class Queen extends Piece {
	public Queen(boolean isWhite) {
		super(isWhite);
		this.pieceType = PieceType.Queen;
	}

	@Override
	public boolean canMove(Board board, Spot start, Spot end) {
		int x1 = start.getX(), y1 = start.getY();
		int x2 = end.getX(), y2 = end.getY();

		return ((Math.abs(x2 - x1) == Math.abs(y2 - y1)) || (x2 == x1 || y2 == y1));
	}
}