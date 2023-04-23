package edu.iastate.cs228.hw1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *  @author Wyatt Duberstein
 *
 */
public class StreamerTest {

    Town t = new Town(4, 4);
    Streamer s = new Streamer(t, 2, 3);

    @Test
    public void testWho() {
        assertEquals(s.who(), State.STREAMER);
    }

    @Test
    public void testNext() {
        t.randomInit(10);
        assertEquals(s.next(t).who(), State.OUTAGE);
    }

}