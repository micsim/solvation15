package de.gwvprojekt.algorithm;

public abstract class AbstractAlgorithm implements Algorithm {
	protected Heuristic heuristic;
	private Node  	    goalNode;
	byte[]              directions;
	
	protected abstract Node getGoalNode(State start, int depth) throws CloneNotSupportedException;
	
	public AbstractAlgorithm(Heuristic h){
		heuristic = h;
		directions = new byte[0];
	}
	
	public boolean findPath(State start) throws OutOfMemoryError, CloneNotSupportedException{
		return findPath(start, 0);
	}
	
	public boolean findPath(State startState, int depth) throws OutOfMemoryError, CloneNotSupportedException{
		if(!startState.isSolvable())
			return false;
		
		try{
			goalNode = getGoalNode(startState, depth);
			calculateDirections();
		}catch (OutOfMemoryError e){
			goalNode = null;
			directions = new byte[0];
			throw(e);
		}catch (CloneNotSupportedException e){
			goalNode = null;
			directions = new byte[0];
			throw(e);
		}
		
		return goalNode != null; // Have we found a goal node?
	}
	
	public void move(byte direction) throws CloneNotSupportedException{
		if(direction == directions[0])
			goalNode = goalNode.getPredecessor();
		else
			findPath(goalNode.getState().move(direction));
	}
	
	public void calculateDirections(){
		int length = 0;
		Node current = goalNode;
		
		while(current != null){
			length++;
			current = current.getPredecessor();
		}
		// Get the number of nodes from the goal note to the starting node.
		
		if(length > 0)
			length--;
		
		directions = new byte[length];
		// Create the array containing the directions.
		current = goalNode;
		
		for(int i=1;i<=length;i++){
			directions[length - i] = current.getDirection();
			// Fill the array from the back.
			current = current.getPredecessor();
			// Loop through the nodes from goal to start.
		}
	}
	
	public State getState(){
		return goalNode.getState();
	}

	@Override
	public String getHintString(){
		if(directions.length == 0)
			return "";
		else
			return directionText(directions[0]);
	}

	@Override
	public String getSolveString() {		
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
	
	/**
	 * Get the string representation of the direction.
	 * 
	 * @param direction
	 * @return String
	 */
	private static String directionText(byte direction){
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
