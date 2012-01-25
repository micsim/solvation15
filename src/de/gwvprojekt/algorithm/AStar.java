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
	
	public boolean findPath(State currentState) throws CloneNotSupportedException{
		goalNode = getGoalNode(currentState);
		
		return goalNode != null;
	}
	
	public void move(int direction) throws CloneNotSupportedException{
		if(direction == AStar.inverseDirection(goalNode.getDirection()))
			goalNode = goalNode.getPredecessor();
		else
			goalNode = getGoalNode(goalNode.getState().move(direction));
	}
	
	public int getHintDirection(){
		if(goalNode == null)
			return 0;
		else
			return AStar.inverseDirection(goalNode.getDirection());
	}
	
	public State getState(){
		return goalNode.getState();
	}
	
	private Node getGoalNode(State goal) throws CloneNotSupportedException{
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

	public static int inverseDirection(int direction){
		if(direction > 0 && direction < 5)
			return 5 - direction;
		else
			return 0;
	}

	@Override
	public String getHintString() {
		return directionText(getHintDirection());
	}

	@Override
	public String getSolveString() {
		String text = "";
		
		Node currentNode = goalNode;
		while(currentNode != null){
			text += ", " + directionText(inverseDirection(currentNode.getDirection()));
		}
		
		return text;
	}
	
	private static String directionText(int direction){
		String text;
		
		switch(direction){
		case 1:
			text = "Up";
			break;
		case 2:
			text = "Left";
			break;
		case 3:
			text = "Right";
			break;
		case 4:
			text = "Down";
			break;
			default:
				text = "";
		}
		
		return text;
	}
}