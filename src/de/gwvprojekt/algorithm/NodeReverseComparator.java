package de.gwvprojekt.algorithm;

import java.util.Comparator;

/**
 * Gives the reverse of the natural order.
 */
public class NodeReverseComparator implements Comparator<Node> {
	@Override
	public int compare(Node arg0, Node arg1) {
		return arg1.compareTo(arg0);
	}
}
