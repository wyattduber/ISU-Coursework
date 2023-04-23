package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;

/**
 *  @author Wyatt Duberstein
 *
 */
public class TownCellTest {

    private Town t = new Town(4, 4);
    private int[] nCensus = {1, 2, 1, 1, 0};
    private Reseller r = new Reseller(t, 0, 1);

    @Test
    public void testCensus() {
        int[] nCensus2 = new int[5];
        t.randomInit(10);
        r.census(nCensus2);
        assertEquals(Arrays.toString(nCensus2), Arrays.toString(this.nCensus));
    }

}
