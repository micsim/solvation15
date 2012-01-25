package de.gwvprojekt;

import de.gwvprojekt.algorithm.DataImpl;
import de.gwvprojekt.ui.Data;
import de.gwvprojekt.ui.GameWerkzeug;

public class StartUp
{

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Data data = new DataImpl();
		GameWerkzeug _game = new GameWerkzeug(data);
	}

}
