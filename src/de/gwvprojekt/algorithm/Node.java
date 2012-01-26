package de.gwvprojekt.algorithm;

/**
 * A node used by search algorithms to store a state of the puzzle as well as extra information.
 */
public interface Node extends Comparable<Node>{
	/**
	 * The state of the puzzle.
	 * 
	 * @return State
	 */
	public State getState();
	
	/**
	 * The cost estimated by a heuristic function.
	 * 
	 * @return int
	 */
	public int   getEstimatedCost();
	
	/**
	 * Node from wich the search discovered this one.
	 * (Part of the optimal path back to the original state from wich the search came.)
	 * 
	 * @return Node
	 */
	public Node  getPredecessor();
	
	/**
	 * Direction the previous node's empty tile was moved to enter this state.
	 * 
	 * @return byte
	 */
	public byte  getDirection();
	
	/**
	 * Delete the state to save memory.
	 */
	public void  emptyState();
}