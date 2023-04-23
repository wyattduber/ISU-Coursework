package edu.iastate.cs228.hw1;

/**
 * 
 * @author Wyatt Duberstein
 *	Also provide appropriate comments for this class
 *
 */
public abstract class TownCell {

	protected Town plain;
	protected int row;
	protected int col;
	
	
	// constants to be used as indices.
	protected static final int RESELLER = 0;
	protected static final int EMPTY = 1;
	protected static final int CASUAL = 2;
	protected static final int OUTAGE = 3;
	protected static final int STREAMER = 4;
	
	public static final int NUM_CELL_TYPE = 5;
	
	//Use this static array to take census.
	public static final int[] nCensus = new int[NUM_CELL_TYPE];

	public TownCell(Town p, int r, int c) {
		plain = p;
		row = r;
		col = c;
	}
	
	/**
	 * Censuses all cell types in the 3 X 3 neighborhood
	 * Use who() method to get who is present in the neighborhood
	 *  
	 * @param nCensus counts of all customers
	 */
	public void census(int[] nCensus) {
		// zero the counts of all customers
		nCensus[RESELLER] = 0;
		nCensus[EMPTY] = 0;
		nCensus[CASUAL] = 0;
		nCensus[OUTAGE] = 0;
		nCensus[STREAMER] = 0;
		int l = 0;

		TownCell[][] grid = new TownCell[plain.getLength()][plain.getWidth()];

			for (int r = -1; r < 2; r++) {
				for (int c = -1; c < 2; c++) {
					try {
					switch (plain.grid[row + r][col + c].who()) {
						case RESELLER:
							if ((row + r) == 0 && (col + c) == 0) {
								break;
							}
							nCensus[RESELLER] += 1;
							break;
						case EMPTY:
							if ((row + r) == 0 && (col + c) == 0) {
								break;
							}
							nCensus[EMPTY] += 1;
							break;
						case CASUAL:
							if ((row + r) == 0 && (col + c) == 0) {
								break;
							}
							nCensus[CASUAL] += 1;
							break;
						case OUTAGE:
							if ((row + r) == 0 && (col + c) == 0) {
								break;
							}
							nCensus[OUTAGE] += 1;
							break;
						case STREAMER:
							if ((row + r) == 0 && (col + c) == 0) {
								break;
							}
							nCensus[STREAMER] += 1;
							break;
					}
				} catch (Exception e) {
						continue;
					}
			}
		}
	}

	/**
	 * Gets the identity of the cell.
	 *
	 * @return State
	 */
	public abstract State who();

	/**
	 * Determines the cell type in the next cycle.
	 * 
	 * @param tNew: town of the next cycle
	 * @return TownCell
	 */
	public abstract TownCell next(Town tNew);
}
