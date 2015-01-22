package utils;

import static org.junit.Assert.*;
import org.junit.Test;

public class MathUtilsTest { //Conducts JUnit Test for the divide method in the MathUtils class
	@Test
	public void divideTest() {
		assertEquals(MathUtils.divide(1,2),0.5,0);
	}
}
