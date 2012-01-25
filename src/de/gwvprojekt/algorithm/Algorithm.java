package de.gwvprojekt.algorithm;

public interface Algorithm {
	public boolean findPath(State currentState) throws CloneNotSupportedException;
	public State   getState();
	public int[]   getDirections(); // 1 -> up, 2 -> left, 3 -> right, 4 -> down
	public String  getHintString();
	public String  getSolveString();
	public void    move(int direction) throws CloneNotSupportedException;
}