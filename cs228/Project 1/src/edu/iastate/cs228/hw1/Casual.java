package edu.iastate.cs228.hw1;

/**
 * @author Wyatt Duberstein
 *
 * Extends the TownCell class to make a Casual Class
 */
public class Casual extends TownCell {

    private Town t;
    private int row;
    private int col;

    public Casual(Town p, int r, int c) {
        super(p, r, c);
        t = p;
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
        return State.CASUAL;
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
        } else if (nCensus[RESELLER] > 0) {
            return new Outage(tNew, row, col);
        } else if (nCensus[STREAMER] > 0) {
            return new Streamer(tNew, row, col);
        } else if (nCensus[CASUAL] >= 5) {
            return new Streamer(tNew, row, col);
        }

        return new Casual(tNew, row, col);

        /*TownCell[][] grid = new TownCell[t.getLength()][t.getWidth()];
        if (row > 0) {
            for (int r = -1; r < 2; r++) {
                if (col > 0) {
                    for (int c = -1; c < 2; c++) {
                        try {
                            switch (t.grid[row + r][col + c].who()) {
                                case RESELLER:
                                    nCensus[0] += 1;
                                    return new Outage(tNew, row, col);
                                case CASUAL:
                                    nCensus[2] += 1;
                                    break;
                                case STREAMER:
                                    nCensus[4] += 1;
                                    return new Streamer(tNew, row, col);
                            }
                        } catch (Exception e) {
                            continue;
                        }
                    }
                } else {
                    for (int c = 0; c < 2; c++) {
                        try {
                            switch (t.grid[row + r][col + c].who()) {
                                case RESELLER:
                                    nCensus[0] += 1;
                                    return new Outage(tNew, row, col);
                                case CASUAL:
                                    nCensus[2] += 1;
                                    break;
                                case STREAMER:
                                    nCensus[4] += 1;
                                    return new Streamer(tNew, row, col);
                            }
                        } catch (Exception e) {
                            continue;
                        }
                    }
                }
            }
        } else {
            for (int r = 0; r < 2; r++) {
                if (col > 0) {
                    for (int c = -1; c < 2; c++) {
                        try {
                            switch (t.grid[row + r][col + c].who()) {
                                case RESELLER:
                                    nCensus[0] += 1;
                                    return new Outage(tNew, row, col);
                                case CASUAL:
                                    nCensus[2] += 1;
                                    break;
                                case STREAMER:
                                    nCensus[4] += 1;
                                    return new Streamer(tNew, row, col);
                            }
                        } catch (Exception e) {
                            continue;
                        }
                    }
                } else {
                    for (int c = 0; c < 2; c++) {
                        try {
                            switch (t.grid[row + r][col + c].who()) {
                                case RESELLER:
                                    nCensus[0] += 1;
                                    return new Outage(tNew, row, col);
                                case CASUAL:
                                    nCensus[2] += 1;
                                    break;
                                case STREAMER:
                                    nCensus[4] += 1;
                                    return new Streamer(tNew, row, col);
                            }
                        } catch (Exception e) {
                            continue;
                        }
                    }
                }
            }
        }

        if (nCensus[0] + nCensus[2] + nCensus[4] >= 7) {
            return new Reseller(tNew, row, col);
        }

        if (nCensus[2] >= 5) {
            return new Streamer(tNew, row, col);
        }

        return new Casual(tNew, row, col);

         */
    }

}
