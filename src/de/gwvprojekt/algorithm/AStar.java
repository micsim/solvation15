package de.gwvprojekt.algorithm;

import java.util.Collection;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar{
	private State start;
	private Heuristic heuristic;
	
	public AStar(Heuristic h){
		start = State.completedState;
		heuristic = h;
	}
	
	public int getHintDirection(State currentState){
		Node gn = getGoalNode(currentState);
		return State.inverseDirection(gn.getDirection());
	}
	
	public Node getGoalNode(State goal){
		PriorityQueue<Node> frontier = new PriorityQueue<Node>();
		frontier.add(new NodeImpl(heuristic.calculateFor(start), start, null, 0));
		
		Collection<State> closed = new HashSet<State>();
		
		int currentPathCost = 0;
		
		while(!frontier.isEmpty()){
			Node current = frontier.poll();
			closed.add(current.getState());
			if(current.getState() == goal){
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