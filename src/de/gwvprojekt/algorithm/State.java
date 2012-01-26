package de.gwvprojekt.algorithm;

import java.util.Random;

/**
 * Zustand des Puzzles ohne zusätzliche Information.
 */
public interface State
{
	/**
	 * Gibt den Zahlenwert des Feldes and der gegebenen Position.
	 * 
	 * @param row
	 * @param column
	 * @return byte
	 */
	public byte   getValue(byte row, byte column);
	
	/**
	 * Gibt die Position des gegebenen Zahlenwertes in der zweidimensionalen Matrix.
	 * 
	 * @param value
	 * @return byte[]
	 */
	public byte[] getPos(byte value);
	
	/**
	 * Gibt alle möglichen Richtungen, in die man das leere Tile verschieben kann.
	 * 
	 * @return byte[]
	 */
	public byte[] getPossibleDirections();
	
	/**
	 * Erzeugt einen zufälligen Zustand des Puzzles, welcher auch unlösbar sein kann.
	 * 
	 * @param random
	 */
	public void randomizeData(Random random);
	
	/**
	 * Liefert den String eines Matrixfeldes an der Stelle row, column
	 * 
	 * @param int row, int column
	 * @return String
	 */
	public String getStringValue(byte row, byte column);

	/**
	 *  Bewegt das leere Feld in die angegebene richtung, falls möglich.
	 *  Gibt den resultierenden Zustand zurück.
	 *  Das Objekt selbst wird nicht verändert.
	 *  
	 * @param direction
	 * @return State
	 * @throws CloneNotSupportedException
	 */
	public State move(byte direction) throws CloneNotSupportedException; // 1 -> up, 2 -> left, 3 -> right, 4 -> down

	/**
	 * Vertauscht das leere Feld mit dem angegebenen Feld, falls dies möglich ist.
	 * 
	 * @param row
	 * @param column
	 */
	public void move(byte row, byte column);

	/**
	 * Prüft ob Tauschen möglich ist.
	 * 
	 * @param row
	 * @param column
	 * @return
	 */
	public boolean isMovable(byte row, byte column);
	
	public boolean isSolvable();

	/**
	 * Initialisiert die Matrix mit einer Permutation
	 */
	public void initializeData();
	
	/**
	 * Gibt den gelösten Zustand, damit andere mit diesem verglichen werden können.
	 * 
	 * @return State
	 */
	public State goalState();
	
	/**
	 * Gibt die Anzahl der Tiles, welche sich nicht an dem Platz befinden, in dem
	 * sie sich in dem gelösten Zustand befinden würden.
	 * 
	 * @return byte
	 */
	public byte getNumberOfMisplacedTiles();

	/**
	 * Gibt die Summe aller Manhattan-Distanzen zwischen der Position jedes Tiles
	 * in dem aktuellen und dem glösten Zustand.
	 * 
	 * @return int
	 */
	public int getManhattanSum();
}