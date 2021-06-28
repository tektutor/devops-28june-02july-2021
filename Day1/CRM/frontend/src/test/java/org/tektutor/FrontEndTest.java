package org.tektutor;

import org.junit.Test;
import static org.junit.Assert.*;

public class FrontEndTest {

	@Test
	public void testCase() {
		FrontEnd fe = new FrontEnd();

		String actualResult = fe.getModuleName();
		String expectedResult = "FrontEnd Module";

		assertEquals ( expectedResult, actualResult );
	}

}