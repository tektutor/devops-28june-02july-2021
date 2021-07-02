package org.tektutor;

public class Main {

	public String getModuleName() {
		return "Main Module";
	}

	public static void main(String[] args) {
		
		FrontEnd fe = new FrontEnd();
		BusinessLayer bl = new BusinessLayer();
		BackEnd be = new BackEnd();

	 	System.out.println ( fe.getModuleName() );
	 	System.out.println ( bl.getModuleName() );
	 	System.out.println ( be.getModuleName() );

	}
}