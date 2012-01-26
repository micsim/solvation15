package de.gwvprojekt.algorithm;

public abstract class AbstractAlgorithm implements Algorithm {
	protected Heuristic heuristic;
	private Node  	    goalNode;
	byte[]              directions;
	
	protected abstract Node getGoalNode(State start) throws CloneNotSupportedException;
	
	public AbstractAlgorithm(Heuristic h){
		heuristic = h;
	}
	
	public boolean findPath(State startState) throws CloneNotSupportedException{
		if(!startState.isSolvable())
			return false;
		
		goalNode = getGoalNode(startState);
		calculateDirections();
		
		return goalNode != null;
	}
	
	public void move(byte direction) throws CloneNotSupportedException{
		if(direction == directions[0])
			goalNode = goalNode.getPredecessor();
		else
			goalNode = getGoalNode(goalNode.getState().move(direction));
	}
	
	public void calculateDirections(){
		int length = 0;
		Node current = goalNode;
		
		while(current != null){
			length++;
			current = current.getPredecessor();
		}
		
		if(length > 0)
			length--;
		
		directions = new byte[length];
		current = goalNode;
		
		for(int i=1;i<=length;i++){
			directions[length - i] = current.getDirection();
			current = current.getPredecessor();
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
