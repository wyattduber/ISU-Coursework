package edu.iastate.cs228.hw1;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResellerTest {

    Town t = new Town(4, 4);
    Reseller r = new Reseller(t, 0, 1);

    @Test
    public void testWho() {
        assertEquals(r.who(), State.RESELLER);
    }

    @Test
    public void testNext() {
        t.randomInit(10);
        assertEquals(r.next(t).who(), State.EMPTY);
    }

}