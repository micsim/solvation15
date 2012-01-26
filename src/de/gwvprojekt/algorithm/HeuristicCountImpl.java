package de.gwvprojekt.algorithm;

/**
 * Heuristic giving the number of misplaced tiles.
 * 
 */
public class HeuristicCountImpl implements Heuristic {
	@Override
	public int calculateFor(State s) {
		return s.getNumberOfMisplacedTiles();
	}
}
