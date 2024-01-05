package src.models.piece;

import src.models.Board;
import src.models.Spot;

public abstract class Piece {
	private boolean isWhite;
	private boolean isKilled;
	protected PieceType pieceType;

	public Piece(boolean isWhite) {
		this.isWhite = isWhite;
		this.isKilled = false;
		this.pieceType = PieceType.Empty;
	}

	public boolean getIsWhite() {
		return isWhite;
	}

	public boolean getIsKilled() {
		return isKilled;
	}

	public void setIsKilled(boolean isKilled) {
		this.isKilled = isKilled;
	}

	public PieceType getPieceType() {
		return pieceType;
	}

	public void setPieceType(PieceType pieceType) {
		this.pieceType = pieceType;
	}

	public abstract boolean canMove(Board board, Spot start, Spot end);
}