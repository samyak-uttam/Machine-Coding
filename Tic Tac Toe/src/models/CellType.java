package src.models;

import java.util.Map;
import java.util.HashMap;

public enum CellType {
	START("-"),
	CROSS("X"),
	ZERO("O");

	private static final Map<String, CellType> BY_LABEL  = new HashMap<>();

	static {
		for (CellType cellType : values()) {
			BY_LABEL.put(cellType.label, cellType);
		}
	}

	public final String label;

	private CellType(String label) {
		this.label = label;
	}

	public static CellType valueOfLabel(String label) {
		return BY_LABEL.get(label);
	}
}