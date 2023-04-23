package edu.iastate.cs228.hw1;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import static edu.iastate.cs228.hw1.TownCell.*;

/**
 * @author Wyatt Duberstein
 *
 * The ISPBusiness class performs simulation over a grid 
 * plain with cells occupied by different TownCell types.
 *
 */
public class ISPBusiness {

	private static int[] nCensus = new int[5];
	private static int totalProfit;
	private static int profit;
	
	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * @param tOld: old/current Town object.
	 * @return: New town object.
	 */
	public static Town updatePlain(Town tOld) {
		Town tNew = new Town(tOld.getLength(), tOld.getWidth());
		for (int r = 0; r < tOld.getLength(); r ++) {
			for (int c = 0; c < tOld.getWidth(); c++) {
				tNew.grid[r][c] = tOld.grid[r][c].next(tNew);
			}
		}
		return tNew;
	}
	
	/**
	 * Returns the profit for the current state in the town grid.
	 * @param town
	 * @return
	 */
	public static int getProfit(Town town) {
		nCensus[2] = 0;
		int row = town.getLength();
		int col = town.getWidth();
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
					if (town.grid[r][c].who() == State.CASUAL) {
						nCensus[2] += 1;
					}
			}
		}
		return nCensus[2];
	}
	

	/**
	 * Main method. Interact with the user and ask if user wants to specify elements of grid
	 *  via an input file (option: 1) or wants to generate it randomly (option: 2).
	 *  
	 *  Depending on the user choice, create the Town object using respective constructor and
	 *  if user choice is to populate it randomly, then populate the grid here.
	 *  
	 *  Finally: For 12 billing cycle calculate the profit and update town object (for each cycle).
	 *  Print the final profit in terms of %. You should only print the integer part (Just print the 
	 *  integer value. Example if profit is 35.56%, your output should be just: 35).
	 *  
	 * Note that this method does not throws any exception, thus you need to handle all the exceptions
	 * in it.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String []args) {
		//TODO: Write your code here.
		Scanner s = new Scanner(System.in);
		String str = "";
		int itr = 0;
		Town t = null;

		int r;
		int c;
		int seed;
		boolean inDone = false;
		boolean outDone = false;
		while (!outDone) {
			System.out.println("How to populate grid (type 1 or 2): 1: from a file. 2: randomly with seed");
			str = s.next();
			switch (str) {
				case "1":
					while (!inDone) {
						try {
							System.out.println("Please enter file path:");
							str = s.next();
							t = new Town(str);
							inDone = true;
						} catch (Exception e) {
							System.out.println("File not found! Please try again.");
						}
					}
					outDone = true;
					break;
				case "2":
					System.out.println("Provide rows, columns and the seed integer separated by spaces:");
					r = s.nextInt();
					c = s.nextInt();
					seed = s.nextInt();
					t = new Town(r, c);
					t.randomInit(seed);
					outDone = true;
					break;
				default:
					System.out.println("Please enter only a 1 or a 2!");
			}
		}

		System.out.println("Start:");
		System.out.println();
		itr = 1;
		System.out.println(t.toString());
		profit = getProfit(t);
		totalProfit += profit;
		System.out.println("Profit: " + profit);
		System.out.println("After itr: " + itr);
		System.out.println();
		for (int i = 0; i < 10; i++) { // Iterations 2 through 11
			Town t2 = updatePlain(t);
			System.out.println(t2.toString());
			profit = getProfit(t2);
			System.out.println("Profit: " + profit);
			totalProfit += profit;
			itr += 1;
			System.out.println("After itr: " + itr);
			System.out.println();
			t = t2;
		}

		//Iteration 12, also includes Profit Utilization
		Town t2 = updatePlain(t);
		System.out.println(t2.toString());
		profit = getProfit(t2);
		System.out.println("Profit: " + profit);
		totalProfit += profit;
		totalProfit /= 12;
		itr += 1;
		System.out.println("After itr: " + itr);
		System.out.println();
		System.out.println("Profit % is: " + (100 * totalProfit) / (t.getLength() * t.getWidth()));
		t = t2;

		s.close();
	}

}
