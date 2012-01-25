package de.gwvprojekt;

import de.gwvprojekt.algorithm.DataImpl;
import de.gwvprojekt.ui.Data;
import de.gwvprojekt.ui.GameUI;

public class StartUp
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Data data = new DataImpl();
		GameUI _ui = new GameUI(data);
	}

}
