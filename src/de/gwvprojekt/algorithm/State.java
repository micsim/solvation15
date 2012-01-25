package de.gwvprojekt.algorithm;

public abstract class State implements Comparable<State>
{
	static State COMPLETED_STATE = null;

	public abstract int   getValue(int row, int column);
	public abstract int[] getPos(int value);
	public abstract State move(int direction); // 1 -> up, 2 -> left, 3 -> right, 4 -> down
	public abstract int[] getPossibleDirections();
	
	public static int inverseDirection(int direction){
		if(direction > 0 && direction < 5)
			return 5 - direction;
		else
			return 0;
	}
}