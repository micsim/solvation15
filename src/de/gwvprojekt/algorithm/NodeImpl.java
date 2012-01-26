package de.gwvprojekt.algorithm;

public class NodeImpl implements Node {
	int estimatedTotalCost;
	int costToHere;
	State state;
	Node predecessor;
	byte moveDirection;
	
	public NodeImpl(int costTo, int ec, State s, Node p, byte direction){
		costToHere = costTo;
		estimatedTotalCost = ec;
		state = s;
		predecessor = p;
		moveDirection = direction;
	}

	public int compareTo(Node o) {
		if(o == null)
			throw new NullPointerException();

		int oec = o.getEstimatedTotalCost();
		if(estimatedTotalCost < oec)
			return -1;
		else if(estimatedTotalCost > oec)
			return 1;
		else
			return 0;
	}

	public int getEstimatedTotalCost() {
		return estimatedTotalCost;
	}
	
	public int getCostTo() {
		return costToHere;
	}

	public State getState() {
		return state;
	}

	public Node getPredecessor() {
		return predecessor;
	}

	public byte getDirection() {
		return moveDirection;
	}

	@Override
	public void emptyState() {
		state = null;
	}
}
