package display;

public class Program {

	// @formatter:off
	public static final String[] grid = {
			"0 0 0 0 0 0",
			"0 1 1 0 0 0",
			"0 1 1 0 0 0",
			"0 0 0 1 1 0",
			"0 0 0 1 1 0",
			"0 0 0 0 0 0" };
	// @formatter:on

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
	
	public static final int[] born = { 3 };
	public static final int[] survive = { 2, 3 };

	/**
	 * Runs a GOL simulation with an example grid and rules.
	 * 
	 * @param args Command line arguments.
	 */
	public static void main(String args[]) {
		LifeGui.launchGui(grid, born1, survive1);
	}

}
