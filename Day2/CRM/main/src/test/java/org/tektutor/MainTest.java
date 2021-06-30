package org.tektutor;

import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {

	@Test
	public void testCase() {
		Main mainModule = new Main();

		String actualResult = mainModule.getModuleName();
		String expectedResult = "Main Module";

		assertEquals ( expectedResult, actualResult );
	}

}