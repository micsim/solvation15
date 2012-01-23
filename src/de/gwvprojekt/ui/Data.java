package de.gwvprojekt.ui;


public interface Data
{
	public String getStringValue(int row, int column);

	public void move(int row, int column);

	public boolean isMovable(int row, int column);

	public void initializeData();
}
