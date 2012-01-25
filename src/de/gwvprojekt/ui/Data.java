package de.gwvprojekt.ui;


public interface Data
{
	public void randomizeData();
	
	/**
	 * Liefert den String eines Matrixfeldes an der Stelle row, column
	 * 
	 * @param int row, int column
	 * @return string
	 */
	public String getStringValue(int row, int column);

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
}
