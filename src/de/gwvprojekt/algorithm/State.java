package de.gwvprojekt.algorithm;

import java.util.Random;

public interface State
{
	public abstract int   getValue(int row, int column);
	public abstract int[] getPos(int value);
	public abstract int[] getPossibleDirections();
	
	public void randomizeData(Random random);
	
	/**
	 * Liefert den String eines Matrixfeldes an der Stelle row, column
	 * 
	 * @param int row, int column
	 * @return string
	 */
	public String getStringValue(int row, int column);

	public State move(int direction) throws CloneNotSupportedException; // 1 -> up, 2 -> left, 3 -> right, 4 -> down

	/**
	 * vertauscht das leere Feld mit dem angegebenen Feld, falls dies möglich ist
	 * @param row
	 * @param column
	 */
	public void move(int row, int column);

	/**
	 * Prüft ob Tauschen möglich ist
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean isMovable(int row, int column);

	/**
	 * Initialisiert die Matrix mit einer Permutation
	 */
	public void initializeData();
	
	public State goalState();
	
	public int getNumberOfMisplacedTiles();
}