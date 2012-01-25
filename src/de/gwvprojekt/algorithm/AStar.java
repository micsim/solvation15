package de.gwvprojekt.algorithm;

import java.util.Collection;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar{
	private State start;
	private State goal;
	private Heuristic heuristic;
	
	AStar(State completedState, State currentState, Heuristic h){
		start = completedState;
		goal = currentState;
		heuristic = h;
	}
	
	public Node getGoalNode(){
		PriorityQueue<Node> frontier = new PriorityQueue<Node>();
		frontier.add(new NodeImpl(heuristic.calculateFor(start), start, null));
		
		Collection<State> closed = new HashSet<State>();
		
		int currentPathCost = 0;
		
		while(!frontier.isEmpty()){
			Node current = frontier.poll();
			closed.add(current.getState());
			if(current.getState() == goal){
				return current;
			}
			
			currentPathCost++;
			for(State neighbor : current.getState().getNeighbors()){
				if(!closed.contains(neighbor)){
					frontier.add(new NodeImpl(currentPathCost + heuristic.calculateFor(neighbor),
							     neighbor,
							     current));
				}
			}
		}
		
		return null;
	}
}