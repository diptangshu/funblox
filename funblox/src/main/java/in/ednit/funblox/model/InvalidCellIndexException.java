package in.ednit.funblox.model;

public class InvalidCellIndexException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3238224934658789152L;
	
	private int x;
	private int y;
	
	public InvalidCellIndexException(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String getMessage() {
		return new StringBuilder("invalid cell index (")
				.append(x).append(", ").append(y).append(")")
				.toString();
	}
}
