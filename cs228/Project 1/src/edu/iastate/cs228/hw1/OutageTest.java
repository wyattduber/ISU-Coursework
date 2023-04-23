package edu.iastate.cs228.hw1;

import org.junit.Test;

import static org.junit.Assert.*;

public class OutageTest {

    Town t = new Town(4, 4);
    Outage o = new Outage(t, 1, 0);

    @Test
    public void testWho() {
        assertEquals(o.who(), State.OUTAGE);
    }

    @Test
    public void testNext() {
        assertEquals(o.who(), State.EMPTY);
    }

}