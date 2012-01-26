package de.gwvprojekt.algorithm;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * A* search algorithm with a fixed size frontier:
 * Once the frontier is full, the most costly node gets deleted on adding another to save space.
 * Does not give the optimal solution (unless the frontier size is not reached),
 * but always gives a possible, near optimal solution.
 * 
 * The same as AStar except for the reverseFrontier and the MAX_FRONTIER_SIZE.
 * See AStar for comments, only differences are commented.
 */
public class LimitedAStar extends AbstractAlgorithm{
	final static int MAX_FRONTIER_SIZE = 1024*1024*8;
	
	public LimitedAStar(Heuristic h) {
		super(h);
	}

	protected Node getGoalNode(State start, int depth) throws CloneNotSupportedException{
		State goal = start.goalState();
		
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(MAX_FRONTIER_SIZE + 1);
		PriorityQueue<Node> reverseFrontier = new PriorityQueue<Node>(MAX_FRONTIER_SIZE + 1,
				                                                      new NodeReverseComparator());
		// Creating frontier for the actual frontier and reverseFrontier for deleting nodes when
		// both are full.
		
		Node newNode = new NodeImpl(0, heuristic.calculateFor(start), start, null, (byte) 0);
		frontier.add(newNode);
		reverseFrontier.add(newNode);
		// Add to both.
		
		Collection<State> closed = new HashSet<State>();
		
		int d = 0;
		
		while(!frontier.isEmpty()){
			d++;
			
			Node current = frontier.poll();
			reverseFrontier.remove(current);
			// Also remove from reverseFrontier.

			closed.add(current.getState());
			
			if(current.getState().equals(goal) || (depth > 0 && d >= depth)){
				return current;
			}
			
			int currentPathCost = current.getCostTo();
			for(byte direction : current.getState().getPossibleDirections()){
				State neighbor = current.getState().move(direction);
				if(!closed.contains(neighbor)){
					newNode = new NodeImpl(currentPathCost,
							               currentPathCost + heuristic.calculateFor(neighbor),
			     			  			   neighbor,
			     			  			   current,
			     			  			   direction);
					frontier.add(newNode);
					reverseFrontier.add(newNode);
					
					if(frontier.size() > MAX_FRONTIER_SIZE){
						// If the maximum size is reached.
						Node toRemove = reverseFrontier.poll();
						// Extract the worst node from reverseFrontier.
						frontier.remove(toRemove);
						// Also remove it from frontier.
					}
				}
			}
			
			current.emptyState();
		}
		
		return null;
	}
}