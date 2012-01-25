package de.gwvprojekt.algorithm;

import java.util.Collection;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar implements Algorithm{
	private Heuristic heuristic;
	private Node  	  goalNode;
	
	public AStar(Heuristic h){
		heuristic = h;
	}
	
	public boolean findPath(State startState) throws CloneNotSupportedException{
		goalNode = getGoalNode(startState);
		
		return goalNode != null;
	}
	
	public void move(int direction) throws CloneNotSupportedException{
		if(direction == AStar.inverseDirection(goalNode.getDirection()))
			goalNode = goalNode.getPredecessor();
		else
			goalNode = getGoalNode(goalNode.getState().move(direction));
	}
	
	public int[] getDirections(){
		int length = 0;
		Node current = goalNode;
		
		while(current != null){
			length++;
			current = current.getPredecessor();
		}
		
		int[] directions = new int[length];
		current = goalNode;
		
		for(int i=1;i<=length;i++){
			directions[length - i] = current.getDirection();
			current = current.getPredecessor();
		}
		
		return directions;
	}
	
	public State getState(){
		return goalNode.getState();
	}
	
	private Node getGoalNode(State start) throws CloneNotSupportedException{
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

	public static int inverseDirection(int direction){
		if(direction > 0 && direction < 5)
			return 5 - direction;
		else
			return 0;
	}

	@Override
	public String getHintString() {
		return directionText(getDirections()[0]);
	}

	@Override
	public String getSolveString() {		
		int[] directions = getDirections();
		if(directions.length == 0)
			return "";
		else{
			String text = directionText(directions[0]);
			for(int i=1;i<directions.length;i++){
				text += ", " + directionText(directions[i]);
			}
			
			return text;
		}
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