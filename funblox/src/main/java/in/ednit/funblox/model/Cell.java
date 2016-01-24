package in.ednit.funblox.model;

public enum Cell {
	EMPTY(0), 
	SHAPE_O(1), 
	SHAPE_I(2), 
	SHAPE_S(3), 
	SHAPE_Z(4), 
	SHAPE_L(5), 
	SHAPE_J(6), 
	SHAPE_T(7);
	
	private final int value;
	
	private Cell(int value) {
		this.value = value;
	}

	public boolean isEmpty() {
		return this == EMPTY;
	}
	
	@Override
	public String toString() {
		return "" + " OISZLJT".charAt(value);
	}
}
