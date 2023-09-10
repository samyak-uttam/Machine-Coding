package src.models.piece;

import src.models.Spot;
import src.models.Board;

public class Knight extends Piece {
	public Knight(boolean isWhite) {
		super(isWhite);
	}

	@Override
	public boolean canMove(Board board, Spot start, Spot end) {
		if (!validateMove(board, start, end))
			return false;

		int x1 = start.getX(), y1 = start.getY();
		int x2 = end.getX(), y2 = end.getY();

		return Math.abs(x2 - x1) * Math.abs(y2 - y1) == 2;
	}
}