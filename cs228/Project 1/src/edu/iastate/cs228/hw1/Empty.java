package edu.iastate.cs228.hw1;

/**
 * @author Wyatt Duberstein
 *
 * Extends the TownCell class to make a Empty Class
 */
public class Empty extends TownCell {

    private int row;
    private int col;

    public Empty(Town p, int r, int c) {
        super(p, r, c);
        row = r;
        col = c;
    }

    /**
     * Gets the identity of the cell.
     *
     * @return State
     */
    @Override
    public State who() {
        return State.EMPTY;
    }

    /**
     * Determines the cell type in the next cycle.
     *
     * @param tNew : town of the next cycle
     * @return TownCell
     */
    @Override
    public TownCell next(Town tNew) {
        int[] nCensus = new int[5];
        census(nCensus);
        if (nCensus[EMPTY] + nCensus[OUTAGE] <= 1) {
            return new Reseller(tNew, row, col);
        }
        return new Casual(tNew, row, col);
    }
}
