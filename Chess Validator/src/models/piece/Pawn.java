package src.models.piece;

import src.models.Spot;
import src.models.Board;
import src.models.piece.PieceType;

public class Pawn extends Piece {
	public Pawn(boolean isWhite) {
		super(isWhite);
		this.pieceType = PieceType.Pawn;
	}

	@Override
	public boolean canMove(Board board, Spot start, Spot end) {
		int x1 = start.getX(), y1 = start.getY();
		int x2 = end.getX(), y2 = end.getY();

		return (y1 == y2 && Math.abs(x2 - x1) <= 2) || (Math.abs(y2 - y1) == 1 && Math.abs(x2 - x1) == 1);
	}
}