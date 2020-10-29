package pt.iscte.paddle.model.cfg.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.cfg.INode;

public class Node implements INode {
	private INode next;
	private Set<INode> incomming = new HashSet<>();
	
	@Override
	public INode getNext() {
		return next;
	}

	@Override
	public IProgramElement getElement() {
		return null;
	}
	
	public void setNext(INode next) {
		assert this.next == null;
		this.next = next;
		((Node) next).incomming.add(this);
	}
	
	@Override
	public Set<INode> getIncomming() {
		return Collections.unmodifiableSet(incomming);
	}
	
	@Override
	public String toString() {
		return isEntry() ? "ENTRY -> " + next.getElement() : isExit() ? "EXIT" : super.toString();
	}
	
	public boolean isEntry() {
		return getElement() == null && getIncomming().isEmpty();
	}
	
	public boolean isExit() {
		return getElement() == null && getNext() == null;
	}
	
	@Override
	public boolean isEquivalentTo(INode node) {
		return 
				getClass().equals(node.getClass()) &&
				(
				getElement() == null && node.getElement() == null ||
				getElement() != null && getElement().equals(node.getElement())
				)
				&&
				(
				getNext() == null && node.getNext() == null ||
				getNext() != null && getNext().getElement() == null && node.getNext().getElement() == null ||
				getNext() != null && getNext().getElement() != null && getNext().getElement().equals(node.getNext().getElement())
				);
	}
	
}
