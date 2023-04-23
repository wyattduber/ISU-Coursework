package edu.iastate.cs228.hw1;

/**
 * @author Wyatt Duberstein
 */
public class Outage extends TownCell {

    private int row;
    private int col;

    public Outage(Town p, int r, int c) {
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
        return State.OUTAGE;
    }

    /**
     * Determines the cell type in the next cycle.
     *
     * @param tNew : town of the next cycle
     * @return TownCell
     */
    @Override
    public TownCell next(Town tNew) {
        return new Empty(tNew, row, col);
    }
}
