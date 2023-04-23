package lab7;

import org.junit.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class SparseArrayTestClass {

    SparseArray<Object> array;

    @Before
    public void create() {
        array = new SparseArray<>();
    }

	@Test
    public void handlesInsertionInDescendingOrder() {
        array.put(7, "seven");
        array.checkInvariants();
        array.put(6, "six");
        array.checkInvariants();
        assertThat(array.get(6), equalTo("six"));
        assertThat(array.get(7), equalTo("seven"));
    }

    @Test
    public void testCase1() {
        array.put(0, null);
        array.checkInvariants();
        assertThat(array.size(), equalTo(0));
    }

    @Test
    public void testCase2() {
        array.put(6, "seis");
        array.put(6, "six");
        assertThat(array.get(6), equalTo("six"));
    }
	     
}