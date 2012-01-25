package de.gwvprojekt.algorithm;

import java.util.Collection;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar extends AbstractAlgorithm{
	public AStar(Heuristic h) {
		super(h);
	}

	protected Node getGoalNode(State start) throws CloneNotSupportedException{
		State goal = start.goalState();
		
		PriorityQueue<Node> frontier = new PriorityQueue<Node>();
		frontier.add(new NodeImpl(heuristic.calculateFor(start), start, null, 0));
		
		Collection<State> closed = new HashSet<State>();
		
		int currentPathCost = 0;
		
		while(!frontier.isEmpty()){
			Node current = frontier.poll();
			closed.add(current.getState());
			if(current.getState().equals(goal)){
				return current;
			}
			
			currentPathCost++;
			for(int direction : current.getState().getPossibleDirections()){
				State neighbor = current.getState().move(direction);
				if(!closed.contains(neighbor)){
					frontier.add(new NodeImpl(currentPathCost + heuristic.calculateFor(neighbor),
							     			  neighbor,
							     			  current,
							     			  direction));
				}
			}
		}
		
		return null;
	}
}