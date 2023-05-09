package hw2;


import java.util.Random;

import static hw2.Status.*;


/**
 * A Cave map arranged in a 2D grid.
 * 
 */
public class Map {
	/**
	 * The cells for this map.
	 */
	private Status[][] cells;
	private Location key;
	private Location exit;



	/**
	 * Constructs a map based on a 2D grid. The given strings, rows, represent rows
	 * of a map, where '#' represents a wall, a blank represents an open square. 
	 * 'X' represents the goal cell or the exit of the cave, etc. 
	 * 
	 * @param rows a given map 
	 */
	public Map(String[] rows) {
		int width = rows[0].length();
		int height = rows.length;
		cells = new Status[height][width];
		for (int row = 0; row < height; ++row) {
			String s = rows[row];
			for (int col = 0; col < width; ++col) {
				char c = s.charAt(col);
				cells[row][col] = char2Status(c);
				if(cells[row][col]==KEY)
					key = new Location(row, col);
				else if(cells[row][col]==EXIT)
					exit = new Location(row, col);
			}
		}
	}
	
	/**
	 * The map when stored in a String array, has many symbols. This method
	 * convert a char (read from a String) to the actual Status that it represents. 
	 */
	private static Status char2Status(char c) {
		switch(c) {
		case 'X':
			return EXIT;
		case 'K':
			return KEY;
		case '#':
			return WALL;
		case 'D':
			return DOOR;
		case 'J':
			return JACKET;
		case 'W':
			return WATER;
		case 'P':
			return PIT;
		case 'F':
			return FOOD;
		case 'M':
			return MATCH;
		case '@':
			return PlAYER;
		default:
			return OPEN;
		}
	}
	
	private static char status2char(Status s) {
		switch(s) {
		case EXIT: return 'X';
		case KEY: return 'K';
		case WALL: return '#';
		case DOOR: return 'D';
		case JACKET: return 'J';
		case WATER: return 'W';
		case PIT: return 'P';
		case FOOD: return 'F';
		case MATCH: return 'M';
		case MARK: return 'R';
		case PlAYER: return '@';
		default: // OPEN
			return ' ';
		}
	}
	/**
	 * Returns the cell Status at the given position. Anything outside the boundary is WALL.
	 * 
	 * @param p position on the map
	 * @return the cell status at given position
	 */
	public Status getCellStatus(Location p) {
		if(p.getCol()<0 || p.getCol()>=cells[0].length || p.getRow()<0 || p.getRow()>=cells.length)
			return Status.WALL;
		return cells[(int) p.getRow()][(int) p.getCol()];
	}

	/**
	 * Mark the cell at this location.  
	 * @param p a Location
	 * @param s a new Status
	 */
	public void mark(Location p, Status s) {
		cells[(int) p.getRow()][(int) p.getCol()] = s;
	}
	
	/**
	 * check if a given location is Wall
	 * @return true if it is or false otherwise.
	 */
	public boolean isWall(Location p) {
		return cells[(int) p.getRow()][(int) p.getCol()] == Status.WALL;
	}
	
	/**
	 * check if a given location is DOOR.
	 * @return true if it is or false otherwise.
	 */
	public boolean isDoor(Location p) {
		return 	cells[(int) p.getRow()][(int) p.getCol()] == Status.DOOR;
	}
	/**
	 * distance to the KEY from the given location. 
	 * @param p current location
	 * @return distance
	 */
	public int distance2key(Location p) {
		return distance(p, key);
	}
	
	/**
	 * distance to the EXIT from the given location. 
	 * @param p current location
	 * @return distance
	 */
	public int distance2exit(Location p) {
		return distance(p, exit);
	}
	
	/**
	 * Manhattan distance between two locations. 
	 * @param a
	 * @param b
	 * @return
	 */
	private static int distance(Location a, Location b) {
		int dc = a.getCol()-b.getCol();
		int dr = a.getRow()-b.getRow();
		return Math.abs(dc) + Math.abs(dr);
	}
	
	/**
	 * Find a random open cell on the map.
	 * @param r a Random generator
	 * @return a position on the map that is OPEN.
	 */
	public Location getRandomOpenLocation(Random r) {
		Location p = null;
		do {
			p = new Location(r.nextInt(cells.length), r.nextInt(cells[0].length));
		} while(getCellStatus(p)!=OPEN);
 
		return p;
	}
	
	/**
	 * return a String representation of the map.
	 * @return the map as String
	 */
	public String toString() {
		String map = "";
		for (int row = 0; row < cells.length; ++row) {
			for (int col = 0; col < cells[0].length; ++col) 
				map += status2char(cells[row][col]);
			map += "\n";
		}
		return map;
	}
}
