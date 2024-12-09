package it.unibo.es2;


public interface Logics {
	
	/**
	 * @return the number of lines/columns
	 */
	int size();
	
	/**
	 * @return the new value a button should show after being pressed
	 */
	String hit(int x, int y);
		
	
	/**
	 * @return whether it is time to quit
	 */
	boolean toQuit();
}
