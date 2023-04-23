package lab6;
import org.junit.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class BearingTest {
	   	   
	   @Test
	   public void answersValidBearing() throws Exception {
	      assertThat(new Bearing(Bearing.MAX).value(), equalTo(Bearing.MAX));
	   }
	   
	   @Test
	   public void answersAngleBetweenItAndAnotherBearing() throws Exception {
	      assertThat(new Bearing(15).angleBetween(new Bearing(12)), equalTo(3));
	   }
	   
	   @Test
	   public void angleBetweenIsNegativeWhenThisBearingSmaller() throws Exception {
	      assertThat(new Bearing(12).angleBetween(new Bearing(15)), equalTo(-3));
	   }

	@Test
	public void partTwoTest1() throws Exception {
		assertThat(new Bearing(0).angleBetween(new Bearing(355)), equalTo(-355));
	}

	@Test
	public void partTwoTest2() throws Exception {
		assertThat(new Bearing(355).angleBetween(new Bearing(90)), equalTo(265));
	}

	@Test
	public void partTwoTest3() throws Exception {
		assertThat(new Bearing(90).angleBetween(new Bearing(55)), equalTo(35));
	}

	@Test
	public void partTwoTest4() throws Exception {
		assertThat(new Bearing(55).angleBetween(new Bearing(100)), equalTo(-45));
	}

	@Test
	public void partTwoTest5() throws Exception {
		assertThat(new Bearing(100).angleBetween(new Bearing(12)), equalTo(88));
	}

	@Test
	public void partTwoTest6() throws Exception {
		assertThat(new Bearing(12).angleBetween(new Bearing(123)), equalTo(-111));
	}
	@Test
	public void partTwoTest7() throws Exception {
		assertThat(new Bearing(123).angleBetween(new Bearing(78)), equalTo(45));
	}
	@Test
	public void partTwoTest8() throws Exception {
		assertThat(new Bearing(78).angleBetween(new Bearing(360)), equalTo(-282));
	}


	   
}
