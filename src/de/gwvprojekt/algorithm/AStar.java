package de.gwvprojekt.algorithm;

import java.util.Collection;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * A* search algorithm. Gives optimal solution but runs out of error in realistic scenarios.
 *
 */
public class AStar extends AbstractAlgorithm{
	public AStar(Heuristic h) {
		super(h);
	}

	protected Node getGoalNode(State start, int depth) throws CloneNotSupportedException{
		State goal = start.goalState();
		// The goal to be reached.
		
		PriorityQueue<Node> frontier = new PriorityQueue<Node>();
		// Frontier represented by a priority queue: Always gives a node with the least cost predicted.
		frontier.add(new NodeImpl(0, heuristic.calculateFor(start), start, null, (byte) 0));
		// Add the starting node.
		
		
		Collection<State> closed = new HashSet<State>();
		// Set of already visited and explored nodes.
		
		int d = 0;
		// Counter to get the depth. (Not the actual depth, but the number of explored nodes.)
		while(!frontier.isEmpty()){
			d++;
			
			Node current = frontier.poll();
			// Extract one of the best nodes from the frontier.
			closed.add(current.getState());
			// Add it's state to the set of explored nodes.
			if(current.getState().equals(goal) || (depth > 0 && d >= depth)){
				// Termination conditions: Goal is reached or critical depth.
				return current;
				// Return current node.
			}
			
			int currentPathCost = current.getCostTo() + 1;
			// The children of this node have one more cost for the path from the staring node.
			for(byte direction : current.getState().getPossibleDirections()){
				// Go through all directions possible.
				State neighbor = current.getState().move(direction);
				// Compute the resulting state from that move.
				if(!closed.contains(neighbor)){
					// If it was not already explored:
					frontier.add(new NodeImpl(currentPathCost,
											  currentPathCost + heuristic.calculateFor(neighbor),
							     			  neighbor,
							     			  current,
							     			  direction));
					// ...Add it to the frontier as a proper node with all needed extra information.
				}
			}
			
			current.emptyState();
			// Delete the stored state to save memory.
		}
		
		return null;
		// If no termination condition was reached the search has failed entirely.
	}
}