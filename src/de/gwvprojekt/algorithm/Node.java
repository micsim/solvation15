package de.gwvprojekt.algorithm;

public interface Node extends Comparable<Node>{
	public int   getEstimatedCost();
	public State getState();
	public Node  getPredecessor();
	public int   getDirection();
}