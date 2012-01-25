package de.gwvprojekt;

import de.gwvprojekt.algorithm.AStar;
import de.gwvprojekt.algorithm.Algorithm;
import de.gwvprojekt.algorithm.HeuristicImpl;
import de.gwvprojekt.algorithm.State;
import de.gwvprojekt.algorithm.StateImpl;
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
		data.randomizeData();
		
		Algorithm alg = new AStar(new HeuristicImpl());
		
		GameWerkzeug _game = new GameWerkzeug(data, alg);
	}

}
