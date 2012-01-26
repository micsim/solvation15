package de.gwvprojekt.algorithm;

public interface Algorithm {
	public boolean findPath(State currentState) throws CloneNotSupportedException;
	public State   getState();
	public String  getHintString();
	public String  getSolveString();
	public void    move(byte direction) throws CloneNotSupportedException;
}