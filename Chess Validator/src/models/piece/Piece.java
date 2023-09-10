package src.models.piece;

public abstract class Piece {
	private boolean isWhite;
	private boolean isKilled;

	public Piece(boolean isWhite) {
		this.isWhite = isWhite;
		this.isKilled = false;
	}

	public boolean getIsWhite() {
		return isWhite;
	}

	public boolean isKilled() {
		return isKilled;
	}

	public void setIsKilled(boolean isKilled) {
		return this.isKilled = isKilled;
	}

	private boolean checkBoundaries(Board board, Spot end) {
		int board_size = board.getBoardSize();
		int x = end.getX(), y = end.getY();
		return (x >= 0 && x < board_size) && (y >= 0 && y < board_size);
	}

	private boolean checkSpot(Spot start, Spot end) {
		return !(start.getX() == end.getX() && start.getY() == end.getY()) && start.isWhite != end.isWhite;
	}

	private boolean validateMove(Board board, Spot start, Spot end) {
		if (start == null || end == null)
			return false;
		return checkBoundaries(board, end) && checkSpot(start, end);
	}

	private boolean checkMiddlePieces(Board board, Spot start, Spot end) {
		int x1 = start.getX(), y1 = start.getY();
		int x2 = end.getX(), y2 = end.getY();

		if (x1 == x2 || y1 == y2) {
			if (x1 == x2) {
				int j = y1 < y2 ? y1 + 1 : y2 + 1;
				for (; j < Math.max(y1, y2); j++) {
					if (board.getSpot(x2, j) != null)
						return false;
				}
			} else {
				int i = y1 < y2 ? y1 + 1 : y2 + 1;
				for (; i < Math.max(x1, x2); i++) {
					if (board.getSpot(i, y2) != null)
						return false;
				}
			}
		} else if (Math.abs(x2 - x1) == Math.abs(y2 - y1)) {
			if ((x1 < x2 && y1 < y2) || (x1 > x2 && y1 > y2)) {
				int i = x1 < x2 ? x1 + 1 : x2 + 1;
				int j = y1 < y2 ? y1 + 1 : y2 + 1;
				for (; i < Math.max(x1, x2) && j < Math.max(y1, y2); i++, j++) {
					if (board.getSpot(i, j) != null)
						return false;
				}
			} else {
				int i = x1 < x2 ? x1 + 1 : x2 + 1;
				int j = y1 > y2 ? y1 - 1 : y2 - 1;
				for (; i < Math.max(x1, x2) && j < Math.min(y1, x2); i++, j--) {
					if (board.getSpot(i, j) != null)
						return false;
				}
			}
		}
		return true;
	}

	public abstract boolean canMove(Board board, Spot start, Spot end);
}