package hw3;

public class TestLife {

	public static final String[] grid1 = {"0 0 0 0 0", 
			  "0 0 1 0 0", 
			  "0 0 1 0 0", 
			  "0 0 1 0 0", 
			  "0 0 0 0 0"};




	public static final String[] grid2 = {"0 0 0 0", 
			  "0 1 1 0", 
			  "0 1 1 0", 
			  "0 0 0 0"};


	public static final String[] grid3 = {"0 0 0 0 0 0", 
			  "0 1 1 0 0 0", 
			  "0 1 1 0 0 0", 
			  "0 0 0 1 1 0",
			  "0 0 0 1 1 0",
			  "0 0 0 0 0 0"};


	public static final String[] grid4 = {"0 0 0 0 0", 
			  "0 0 1 1 0", 
			  "0 1 0 0 0", 
			  "0 1 1 0 0", 
			  "0 0 0 0 0"};

	public static final String[] grid5 = {"0 0 0 0 0 0 0 1", 
			  "0 0 0 0 0 0 1 0", 
			  "0 0 0 0 0 0 1 0",
			  "0 0 0 0 0 0 0 1"};


	//Conways original rules
	public static final int[] born1 = {3};
	public static final int[] survive1 = {2,3};

	//Flock (B3/S12)
	public static final int[] born2 = {3};
	public static final int[] survive2 = {1,2};

	//Replicator (B1357/S1357)
	public static final int[] born3 = {1, 3, 5, 7};
	public static final int[] survive3 = {1,3, 5, 7};

	//Life free or die (B2/S0)
	public static final int[] born4 = {2};
	public static final int[] survive4 = {0};
	
	public static void main(String args[]) {
		/**
		 * Oscillates between
		 * 00000
		 * 00100
		 * 00100
		 * 00100
		 * 00000
		 * 
		 * and
		 * 
		 * 00000
		 * 00000
		 * 01110
		 * 00000
		 * 00000
		 * 
		 * 
		 */
		Life game = new Life(grid1, born1, survive1);
		System.out.println("Grid 1,  B3/S23");
		generate(game, 3);
		
		
		/**
		 * Does not change (a "still life" game)
		 * 0000
		 * 0110
		 * 0110
		 * 0000 
		 * 
		 */
		game = new Life(grid2, born1, survive1);
		System.out.println("Grid 2,  B3/S23");
		generate(game, 3);
		
		
		/**
		 * Oscillates between
		 * 000000
		 * 011000
		 * 011000
		 * 000110
		 * 000110
		 * 000000
		 * 
		 * and
		 * 
		 * 000000
		 * 011000
		 * 010000
		 * 000010
		 * 000110
		 * 000000
		 */
		game = new Life(grid3, born1, survive1);
		System.out.println("Grid 3,  B3/S23");
		generate(game, 3);
		
		
		
		/**
		 * Oscillates between
		 * 00000
		 * 00110
		 * 01000
		 * 01100
		 * 00000
		 * 
		 * and
		 * 
		 * 00000
		 * 00110
		 * 00010
		 * 01100
		 * 00000
		 * 
		 * 
		 */
		game = new Life(grid4, born2, survive2);
		System.out.println("Grid 4,  B3/S12");
		generate(game, 3);
		
		//The "spaceship" moves right across the grid
		/**
		 *  seed          1st gen       2nd gen			3rd gen
		 * 00000001		  00000010		00000100		00001000 
		 * 00000010		  00000100		00001000		00010000
		 * 00000010		  00000100		00001000		00010000
		 * 00000001		  00000010		00000100		00001000
		 * 
		 * 
		 */
		game = new Life(grid5, born4, survive4);
		System.out.println("Grid 5,  B2/S0");
		generate(game, 3);
	}
	
	public static void generate(Life l, int numGeneration) {
		for(int i = 0; i <= numGeneration; i++) {
			System.out.println(l);
			l.nextGeneration();
		}
	}
}
