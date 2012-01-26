package de.gwvprojekt.algorithm;

/**
 * A search algorithm to solve the puzzle.
 */
public interface Algorithm {
	/**
	 * Search for the path from currentState to the solved state.
	 * Returns whether a path could be found.
	 * 
	 * @param currentState
	 * @return boolean
	 * @throws CloneNotSupportedException
	 */
	public boolean findPath(State currentState) throws CloneNotSupportedException;
	
	/**
	 * Get the state maintained by the implementation to be able to research the optimal path
	 * after a move when needed.
	 * 
	 * @return State
	 */
	public State   getState();
	
	/**
	 * Get a string representing the direction of the first move on the path toward the solved state.
	 * 
	 * @return String
	 */
	public String  getHintString();
	
	/**
	 * Get a string representing the complete solution in terms of the directions to move.
	 * 
	 * @return String
	 */
	public String  getSolveString();
	
	/**
	 * Move the maintained state to the given direction.
	 * Research the optimal path if necessary.
	 * 
	 * @param direction
	 * @throws CloneNotSupportedException
	 */
	public void    move(byte direction) throws CloneNotSupportedException;
}