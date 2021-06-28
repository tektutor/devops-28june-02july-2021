package org.tektutor;

import org.junit.Test;
import static org.junit.Assert.*;

public class BackEndTest {

	@Test
	public void testCase() {
		BackEnd fe = new BackEnd();

		String actualResult = fe.getModuleName();
		String expectedResult = "BackEnd Module";

		assertEquals ( expectedResult, actualResult );
	}

}