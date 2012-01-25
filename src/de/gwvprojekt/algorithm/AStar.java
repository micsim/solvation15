package de.gwvprojekt.algorithm;

import java.util.Collection;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar implements Algorithm{
	private State     start;
	private Heuristic heuristic;
	private Node  	  goalNode;
	
	public AStar(State startState, Heuristic h){
		start = startState;
		heuristic = h;
		goalNode = new NodeImpl(heuristic.calculateFor(start), start, null, 0);
	}
	
	public boolean findPath(State currentState){
		goalNode = getGoalNode(currentState);
		
		return goalNode != null;
	}
	
	public void move(int direction){
		if(direction == State.inverseDirection(goalNode.getDirection()))
			goalNode = goalNode.getPredecessor();
		else
			goalNode = getGoalNode(goalNode.getState().move(direction));
	}
	
	public int getHintDirection(){
		if(goalNode == null)
			return 0;
		else
			return State.inverseDirection(goalNode.getDirection());
	}
	
	public State getState(){
		return goalNode.getState();
	}
	
	private Node getGoalNode(State goal){
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