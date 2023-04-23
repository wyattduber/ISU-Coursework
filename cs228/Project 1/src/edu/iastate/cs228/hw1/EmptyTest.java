package edu.iastate.cs228.hw1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *  @author Wyatt Duberstein
 *
 */
public class EmptyTest {

    Town t = new Town(4, 4);
    Empty e = new Empty(t, 1, 0);

    @Test
    public void testWho() {
        assertEquals(e.who(), State.EMPTY);
    }

    @Test
    public void testNext() {
        t.randomInit(10);
        assertEquals(e.next(t).who(), State.CASUAL);
    }

}