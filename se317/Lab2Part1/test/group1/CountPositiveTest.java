package group1;

import static org.junit.Assert.assertEquals;
import org.junit.*;

public class CountPositiveTest {

    @Test
    public void arrayContainsZeroes() {
        int arr[] = {-4, 2, -1, 2};
        assertEquals("Array contains zeroes", 2, CountPositive.countPositive((arr)));
    }

    @Test
    public void proofThatCodeIsBroken() {
        int arr[] = {1, -3, -4, 0, 5, 0, -3};
        assertEquals("Expected 2 positive numbers", 2, CountPositive.countPositive(arr));
    }

}
