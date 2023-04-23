package group1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OddOrPosTest {

    @Test
    public void negativeOddNumbers() {
        int[] arr = {1, 2, 3};
        assertEquals("Negative odd numbers in array", 3, OddOrPos.oddOrPos(arr));
    }

    @Test
    public void betterTest() {
        int[] arr = {-7, -5, -3, 0, 1, 2};
        assertEquals("Negative and odd numbers in the array", 5, OddOrPos.oddOrPos(arr));
    }

}
