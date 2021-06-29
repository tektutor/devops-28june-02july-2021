package org.tektutor;

/**
 * Hello world!
 *
 */
public class App 
{

    public String sayHello() {
	    return "Hello DevOps!";
    }

    public static void main( String[] args )
    {
	    App app = new App();
	    System.out.println ( app.sayHello() );
    }
}
