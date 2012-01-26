package de.gwvprojekt.algorithm;

public class NodeImpl implements Node {
	int estimatedCost;
	State state;
	Node predecessor;
	byte moveDirection;
	
	public NodeImpl(int ec, State s, Node p, byte direction){
		estimatedCost = ec;
		state = s;
		predecessor = p;
		moveDirection = direction;
	}

	public int compareTo(Node o) {
		if(o == null)
			throw new NullPointerException();

		int oec = o.getEstimatedCost();
		if(estimatedCost < oec)
			return -1;
		else if(estimatedCost > oec)
			return 1;
		else
			return 0;
	}

	public int getEstimatedCost() {
		return estimatedCost;
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
}
