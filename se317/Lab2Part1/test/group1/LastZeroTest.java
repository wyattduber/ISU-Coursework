package group1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LastZeroTest {

    @Test
    public void multipleZeroes() {
        int arr[] = {0, 1, 0};
        assertEquals("Multiple zeroes: should find last one", 0, lastZero.lastZero(arr));
    }

    @Test
    public void proofThatThisCodeIsBroken() {
        int[] arr = {3, 0, 2, 1, 0};
        assertEquals("Should find the last 0 in the arr", 4, lastZero.lastZero(arr));
    }

}
