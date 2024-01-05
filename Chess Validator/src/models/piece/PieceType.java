package src.models.piece;

public enum PieceType {
	Pawn("P"),
	Knight("N"),
	Rook("R"),
	Bishop("B"),
	Queen("Q"),
	King("K"),
	Empty("--");

	public final String label;

	private PieceType(String label) {
		this.label = label;
	}
}