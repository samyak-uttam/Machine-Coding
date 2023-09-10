package src.models;

public class TicTacToeBoard {
	private int boardSize;
	private CellType[][] board;

	public TicTacToeBoard(int boardSize) {
		this.boardSize = boardSize;
		board = new CellType[boardSize][boardSize];
		initializeBoard();
	}

	private void initializeBoard() {
		for (int r = 1; r <= boardSize; r++) {
			for (int c = 1; c <= boardSize; c++) {
				setCellValue(r, c, CellType.START);
			}
		}
	}

	public int getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

	public CellType getCellValue(int rowIndex, int colIndex) {
		return board[rowIndex - 1][colIndex - 1];
	}

	private boolean validate(int rowIndex, int colIndex) {
		return (rowIndex >= 1 && rowIndex <= boardSize)
		       && (colIndex >= 1 && colIndex <= boardSize)
		       && (board[rowIndex - 1][colIndex - 1] == null || board[rowIndex - 1][colIndex - 1] == CellType.START);
	}

	public boolean setCellValue(int rowIndex, int colIndex, CellType cellValue) {
		if (validate(rowIndex, colIndex)) {
			board[rowIndex - 1][colIndex - 1] = cellValue;
			return true;
		} else {
			return false;
		}
	}
}