package hw3;

import java.util.Scanner;


import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.File;


public class Life {

	
	/**
	 * Constructs Conways Game of Life give a starting grid (a "seed"), the born rules and the survive rules.  The grid is given as an array of strings as in the following example.
	 * 
	 * 0 0 0 0 0
	 * 0 0 1 0 0
	 * 0 0 1 0 0
	 * 0 0 1 0 0
	 * 0 0 0 0 0
	 * 
	 * @param initConfig an array of Strings encoding the starting grid
	 * @param born an array of integers encoding the rules for a cell being born. 
	 * @param survive an array of integers encoding the rules for a cell surviving. 
	 */
	public Life(String[] initConfig, int[] born, int[] survive) {
		
	}
	
	/**
	 * Constructs Conways Game of Life give a starting grid (a "seed"), the born rules and the survive rules.  The grid is given in a file containing a list of strings as in the following example.
	 * 
	 * 0 0 0 0 0
	 * 0 0 1 0 0
	 * 0 0 1 0 0
	 * 0 0 1 0 0
	 * 0 0 0 0 0
	 * 
	 * @param f the File encoding the starting grid
	 * @param born an array of integers encoding the rules for a cell being born. 
	 * @param survive an array of integers encoding the rules for a cell surviving. 
	 * @throws FileNotFoundException
	 */
	public Life(File f, int[] born, int[] survive) throws FileNotFoundException{
		
		
		
	}
	
	/**
	 * Returns cell at specified position
	 * @param row index of the row 
	 * @param col index of the column
	 * @return Cell at position (row, col)
	 */
	public Cell getCell(int row, int col) {
		return null;
	}
	
	/**
	 * Returns the number of rows in the Game of Life
	 * @return number of rows in grid
	 */
	public int getRows() {
		return 0;
	}
	
	
	/**
	 * Returns the number of columns in the Game of Life
	 * @return number of columns in grid
	 */
	public int getColumns() {
		return 0;
	}
	
	/**
	 * Performs one generation of the game
	 * 
	 */
	public void nextGeneration() {
	
		
	}
	
		


	/**
	 * Returns a String representation of the game. Returns the String representation of each element of the game in a grid.
	 * 
	 * @return a string representing the current state of the game.
	 */
	public String toString() {
		
		return "";
		
	}
	
	

	
}
