package de.gwvprojekt;

import de.gwvprojekt.algorithm.*;
import de.gwvprojekt.ui.GameWerkzeug;

public class StartUp
{

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		State data = new StateImpl();
		//data.randomizeData();
		
		Algorithm alg = new LimitedAStar(new HeuristicCountImpl());
		
		GameWerkzeug _game = new GameWerkzeug(data, alg);
	}

}
