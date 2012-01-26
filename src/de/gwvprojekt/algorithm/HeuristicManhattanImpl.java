package de.gwvprojekt.algorithm;

/**
 * Heuristic giving the manhattan distance from every tile to it's position in the solved state.
 * 
 */
public class HeuristicManhattanImpl implements Heuristic {
	@Override
	public int calculateFor(State s) {
		return s.getManhattanSum();
	}
}
