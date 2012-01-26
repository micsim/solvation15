package de.gwvprojekt.algorithm;

/**
 * Minimal heuristic: Always give 0.
 *
 */
public class HeuristicMinimalImpl implements Heuristic
{
	@Override
	public int calculateFor(State s)
	{
		return 0;
	}
}
