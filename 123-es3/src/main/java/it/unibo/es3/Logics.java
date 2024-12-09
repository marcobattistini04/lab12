package it.unibo.es3;

import java.util.List;

public interface Logics {
	
	/**
	 * @return the number of lines/columns
	 */
	List<Pair<Integer, Integer>> getPositions();
	
	/**
	 * @return the new value a button should show after being pressed
	 */
	void hit();
		
	/**
	 * @return whether it is time to quit
	 */
	boolean toQuit();
}
