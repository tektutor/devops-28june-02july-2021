package org.tektutor;

import org.junit.Test;
import static org.junit.Assert.*;

public class BusinessLayerTest {

	@Test
	public void testCase() {
		BusinessLayer bl = new BusinessLayer();

		String actualResult = bl.getModuleName();
		String expectedResult = "BusinessLayer Module";

		assertEquals ( expectedResult, actualResult );
	}

}