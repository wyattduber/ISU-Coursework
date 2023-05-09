package hw3;

import java.util.ArrayList;
import java.util.Arrays;

public class Cell {

	private boolean alive;
	
	/**
	 * Constructs a new cell in a Game of Life
	 * @param alive the initial state of the cell
	 * @param b an array of integers encoding the born rules for the cell
	 * @param s an array of integers encoding the survive rules for the cell
	 */
	public Cell(boolean alive, int[] b, int[] s) {
		
	}
	
	/**
	 * Returns the boolean value of whether the cell is dead or alive. 
	 * 
	 * @return the boolean value of whether the cell is dead or alive.
	 */
	public boolean isAlive() {
		return false;
	}
	
	/**
	 * Returns the boolean value of whether the cell is dead or alive in the next generation.
	 * 
	 * @return the boolean value of whether the cell is dead or alive in the next generation.
	 */
	public boolean isAliveAfterNextGeneration() {
		return false;
	}
	
	/**
	 * Sets the status of the cell (dead or alive) in the next generation. The status is determined by the born and alive rules.
	 */
	public void setIsAliveNextGeneration() {
		
	}
	
	/**
	 * Sets the neighbors of the cell. Note that the cell is not neighbors with itself.
	 * 
	 *  @param ArrayList of Cells of the neighboring cells.
	 * 
	 */
	public void setNeighbors(ArrayList<Cell> n) {
		  
	}
	
	/**
	 * Gets the neighbors of the cell. Note that the cell is not neighbors with itself.
	 * 
	 *  @return number of the neighboring cells.
	 * 
	 */
	public int getNumNeighbors() {
		  return 0;
	}
	
	/**
	 * Gets the number of neighbors which are alive.
	 * 
	 * @return number of neighboring cells which are alive
	 */
	public int getNumAliveNeighbors() {
		return 0;
	}
	
	/**
	 * Sets the current status of the cell equal to the status of the cell after one generation of the game. 
	 */
	public void nextGeneration() {
		
	}
	
	/**
	 * Returns the survive rule of the cell encoded in an array.
	 * 
	 * @return a copy of the born rule this cell is initialized with.
	 */
	public int[] getBornRule() {
		return null;
	}
	
	/**
	 * Returns the survive rule of the cell encoded in an array.
	 * 
	 * @return a copy of the survive rule this cell is initialized with.
	 */
	public int[] getSurviveRule() {
		return null;
	}
	
	/**
	 * Returns a String representation of the cell. Returns "0" if the cell is dead or "1" if the cell is alive.
	 * 
	 * @return a string representing the current state of the cell.
	 */
	public String toString() {
		if(alive) {
			return "1";
		}
		else {
			return "0";
		}
	}
	
}

