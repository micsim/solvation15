package de.gwvprojekt.algorithm;

import java.util.Collection;
import java.util.HashSet;
import java.util.PriorityQueue;

public class LimitedAStar extends AbstractAlgorithm{
	final static int MAX_FRONTIER_SIZE = 1024*1024*8;
	
	public LimitedAStar(Heuristic h) {
		super(h);
	}

	protected Node getGoalNode(State start, int depth) throws CloneNotSupportedException{
		State goal = start.goalState();
		
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(MAX_FRONTIER_SIZE);
		PriorityQueue<Node> reverseFrontier = new PriorityQueue<Node>(MAX_FRONTIER_SIZE,
				                                                      new NodeReverseComparator());
		
		Node newNode = new NodeImpl(0, heuristic.calculateFor(start), start, null, (byte) 0);
		frontier.add(newNode);
		reverseFrontier.add(newNode);
		
		Collection<State> closed = new HashSet<State>();
		
		int d = 0;
		
		while(!frontier.isEmpty()){
			d++;
			
			Node current = frontier.poll();
//			System.out.println(current.getEstimatedCost());
//			System.out.println(reverseFrontier.peek().getEstimatedCost());
			reverseFrontier.remove(current);

//			Iterator<Node> iter = frontier.iterator();
//			while(iter.hasNext()){
//				Node no = iter.next();
//				System.out.println(no.getEstimatedCost());
//			}
//			System.out.print("Picked: ");
//			System.out.println(current.getEstimatedCost());
			closed.add(current.getState());
			
			if(current.getState().equals(goal) || (depth > 0 && d >= depth)){
				return current;
			}
			
			int currentPathCost = current.getCostTo();
			for(byte direction : current.getState().getPossibleDirections()){
				State neighbor = current.getState().move(direction);
				if(!closed.contains(neighbor)){
					newNode = new NodeImpl(currentPathCost,
							               currentPathCost + heuristic.calculateFor(neighbor),
			     			  			   neighbor,
			     			  			   current,
			     			  			   direction);
					frontier.add(newNode);
					reverseFrontier.add(newNode);
					
					if(frontier.size() > MAX_FRONTIER_SIZE){
						Node toRemove = reverseFrontier.poll();
						frontier.remove(toRemove);
					}
				}
			}
			
			current.emptyState();
		}
		
		return null;
	}
}