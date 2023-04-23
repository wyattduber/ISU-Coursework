package edu.iastate.cs228.hw1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *  @author Wyatt Duberstein
 *
 */
public class CasualTest{

    Town t = new Town(4, 4);
    Casual c = new Casual(t, 1, 2);

    @Test
    public void testWho() {
        assertEquals(c.who(), State.CASUAL);
    }

    @Test
    public void testNext() {
        t.randomInit(10);
        assertEquals(c.next(t).who(), State.OUTAGE);
    }

}
