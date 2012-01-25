package de.gwvprojekt.algorithm;

/**
 * Heuristic giving the number of misplaced tiles.
 * 
 * @author michael
 *
 */
public class HeuristicCountImpl implements Heuristic {
	@Override
	public int calculateFor(State s) {
		return s.getNumberOfMisplacedTiles();
	}
}
