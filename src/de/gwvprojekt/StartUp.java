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
		
		Algorithm alg1 = new AStar(new HeuristicManhattanImpl());
		Algorithm alg2 = new LimitedAStar(new HeuristicManhattanImpl());
		
		GameWerkzeug _game = new GameWerkzeug(data, alg1, alg2);
	}

}
