package de.gwvprojekt.algorithm;

public interface State extends de.gwvprojekt.ui.Data
{
	public abstract int   getValue(int row, int column);
	public abstract int[] getPos(int value);
	public abstract State move(int direction) throws CloneNotSupportedException; // 1 -> up, 2 -> left, 3 -> right, 4 -> down
	public abstract int[] getPossibleDirections();
}