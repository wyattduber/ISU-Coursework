package edu.iastate.cs228.hw1;

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.*;

import java.io.FileNotFoundException;

/**
 *  @author Wyatt Duberstein
 *
 */
public class TownTest {

    private String s = "O R O R \n" +
            "E E C O \n" +
            "E S O S \n" +
            "E O R R \n";
    private Town t = new Town(4, 4);

    @Test
    public void testLength() {
        assertEquals(t.getLength(), 4);
    }

    @Test
    public void testWidth() {
        assertEquals(t.getLength(), 4);
    }

    @Test
    public void testToString() {
        t.randomInit(10);
        assertEquals(t.toString(), s);
    }

    @Test
    public void testRandomInit() {
        t.randomInit(10);
        assertEquals(t.toString(), s);
    }

    @Test
    public void testTownFile() throws FileNotFoundException {
        Town town = new Town("ISP4x4.txt");
        assertEquals(town.toString(), s);
    }

}