package de.gwvprojekt.algorithm;

public class HeuristicManhattanImpl implements Heuristic {
	@Override
	public int calculateFor(State s) {
		return s.getManhattanSum();
	}
}
