package hw2;

/**
 * a 2-D location specified by a row and a column number. 
 *
 */
public class Location {
	private int row;
	private int col;
	
	/**
	 * create a new location.
	 * @param row given row
	 * @param col given col
	 */
	public Location(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	/**
	 * create a deep copy of an old Location.
	 * @param old
	 */
	public Location(Location old) {
		this.row = old.row;
		this.col = old.col;
	}
	
	/**
	 * return the row.
	 * @return row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * return the column
	 * @return column
	 */
	public int getCol() {
		return col;
	}
	
	/**
	 * translate this Location by the specified amount.
	 * @param dr amount along row
	 * @param dc amount along column
	 */
	public void translate(int dr, int dc) {
		row = row + dr;
		col = col + dc;
	}
	
	public String toString() {
		return "[" + row + ", " + col + "]";
	}
	
	
}
