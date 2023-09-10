package src.models;

public class Player {
	private String id;
	private String name;
	private CellType cellType;

	public Player(String id, String name, CellType cellType) {
		this.id = id;
		this.name = name;
		this.cellType = cellType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CellType getCellType() {
		return cellType;
	}

	public void setCellType(CellType cellType) {
		this.cellType = cellType;
	}
}