package de.gwvprojekt.algorithm;

import java.util.Random;

public interface State
{
	public abstract byte   getValue(byte row, byte column);
	public abstract byte[] getPos(byte value);
	public abstract byte[] getPossibleDirections();
	
	public void randomizeData(Random random);
	
	/**
	 * Liefert den String eines Matrixfeldes an der Stelle row, column
	 * 
	 * @param int row, int column
	 * @return string
	 */
	public String getStringValue(byte row, byte column);

	public State move(byte direction) throws CloneNotSupportedException; // 1 -> up, 2 -> left, 3 -> right, 4 -> down

	/**
	 * vertauscht das leere Feld mit dem angegebenen Feld, falls dies möglich ist
	 * @param row
	 * @param column
	 */
	public void move(byte row, byte column);

	/**
	 * Prüft ob Tauschen möglich ist
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean isMovable(byte row, byte column);

	/**
	 * Initialisiert die Matrix mit einer Permutation
	 */
	public void initializeData();
	
	public State goalState();
	
	public byte getNumberOfMisplacedTiles();
	public int  getManhattanSum();
	public boolean solveable();
}