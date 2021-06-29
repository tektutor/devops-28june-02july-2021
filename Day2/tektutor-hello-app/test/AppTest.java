package org.tektutor;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

public class AppTest 
{
    private App app;
    private String expectedResult, actualResult;

    @Before
    public void initialize() {
	app = new App();
    }

    @After
    public void cleanUp() {
	app = null;
    }

    @Test
    public void testSayHello()
    {
	expectedResult = "Hello DevOps!";
	actualResult = app.sayHello();
        assertEquals( expectedResult, actualResult );
    }
}
