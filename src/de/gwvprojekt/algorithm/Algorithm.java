package de.gwvprojekt.algorithm;

public interface Algorithm {
	public boolean findPath(State currentState);
	public State   getState();
	public int     getHintDirection(); // 1 -> up, 2 -> left, 3 -> right, 4 -> down
	public void    move(int direction);
}