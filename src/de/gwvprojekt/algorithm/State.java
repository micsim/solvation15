package de.gwvprojekt.algorithm;

import java.util.Collection;

public interface State extends Comparable<State>
{
	public int getValue(int row, int column);
	public int[] getPos(int value);
	public void move(int row, int column);
	public boolean isMovable(int row, int column);
	public void initializeData();
	
	public Collection<State> getNeighbors();
}