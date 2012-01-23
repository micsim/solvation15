package de.gwvprojekt.core;

public interface Data
{
	public int getValue(int row, int column);
	public int[] getPos(int value);
	public void move(int row, int column);
	public boolean isMovable(int row, int column);
	public void initializeData();
}