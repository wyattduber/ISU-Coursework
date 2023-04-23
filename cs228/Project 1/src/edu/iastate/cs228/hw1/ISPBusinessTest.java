package edu.iastate.cs228.hw1;

import static org.junit.Assert.*;
import org.junit.Test;

public class ISPBusinessTest {

    Town t = new Town(4, 4);

    @Test
    public void testUpdatePlain() {
        Town town = new Town(4, 4);
        t.randomInit(10);
        for (int r = 0; r < t.getLength(); r++) {
            for (int c = 0; c < t.getWidth(); c++) {
                town.grid[r][c] = t.grid[r][c].next(town);
            }
        }
        assertEquals(ISPBusiness.updatePlain(t).toString(), town.toString());
    }

    @Test
    public void testGetProfit() {
        t.randomInit(10);
        assertEquals(ISPBusiness.getProfit(t), 1);
    }

}
