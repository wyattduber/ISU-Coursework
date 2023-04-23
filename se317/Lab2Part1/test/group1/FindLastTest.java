package group1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindLastTest {

    @Test
    public void lastOccurenceInFirstElement() {
        int[] arr = {2, 3, 5};
        int y = 3;
        assertEquals("Last occurence in first element", 1, FindLast.findLast(arr, y));
    }

    @Test
    public void betterTest() {
        int[] arr = {1, 2, 3, 4, 5, 4, 3, 2, 1};
        int y = 2;
        assertEquals("Verify that it's the last occurence", 7, FindLast.findLast(arr, y));
    }

}