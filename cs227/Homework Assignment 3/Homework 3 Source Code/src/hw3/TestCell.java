package hw3;

import java.util.ArrayList;

public class TestCell {

	//Conways original rules
		public static final int[] born1 = {3};
		public static final int[] survive1 = {2,3};
		
		//Flock (B3/S12)
		public static final int[] born2 = {3};
		public static final int[] survive2 = {2};
		
		
		
		public static void main(String[] args) {
			
			Cell c = new Cell(false, born1, survive1);
			//Expected false
			System.out.println(c.isAlive());
			
			/**
			 * Adds three neighboring cells which are alive.
			 */
			ArrayList<Cell> n = new ArrayList<>();
			for(int i = 1; i <= 3; i++) {
				n.add(new Cell(true,born1,survive1));
			}
			c.setNeighbors(n);
			
			//Expected 3
			System.out.println(c.getNumNeighbors());
			//Expected 3
			System.out.println(c.getNumAliveNeighbors());
			
			c.setIsAliveNextGeneration();
			
			// Expected true - there are exactly three alive neighbors of c
			// The next generation of c should be "born"
			System.out.println(c.isAliveAfterNextGeneration());
			
			c.nextGeneration();
			
			//Expected true
			System.out.println(c.isAlive());
			
			System.out.println();
			System.out.println();
			c = new Cell(true, born2, survive2);
			
			//Expected true
			System.out.println(c.isAlive());
			
			/**
			 * Adds three neighboring cells which are alive.
			 */
			n = new ArrayList<>();
			for(int i = 1; i <= 3; i++) {
				n.add(new Cell(true,born1,survive1));
			}
			c.setNeighbors(n);
			
			c.setIsAliveNextGeneration();
			
			// Expected false - there are exactly three alive neighbors of c
			// The next generation of c should die due to "overpopulation"
			System.out.println(c.isAliveAfterNextGeneration());
			
			c.nextGeneration();
			
			//Expected false
			System.out.println(c.isAlive());
			
			c.setIsAliveNextGeneration();
			
			// Expected true - there are exactly three alive neighbors of c
			// The next generation of c should be "born"
			System.out.println(c.isAliveAfterNextGeneration());
			
			c.nextGeneration();
			
			//Expected true
			System.out.println(c.isAlive());
			
		}

}
