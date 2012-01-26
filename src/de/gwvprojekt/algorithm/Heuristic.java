package de.gwvprojekt.algorithm;

/**
 * A heuristic that can be used by a search.
 * It should not overestimate the cost from any state to the goal.
 */
public interface Heuristic {
	/**
	 * Cost estimated for State s.
	 * Should be <= the actual cost.
	 * 
	 * @param s
	 * @return int
	 */
	int calculateFor(State s);
}
