package pt.iscte.paddle.model.cfg;

import java.util.Set;

import pt.iscte.paddle.model.IProgramElement;

public interface INode {
	INode getNext(); // sequence or false
	Set<INode> getIncomming();
	
	
	void setNext(INode node);
	
	IProgramElement getElement();
	
	default boolean isEntry() {
		return getElement() == null && getIncomming().isEmpty();
	}
	
	default boolean isExit() {
		return getElement() == null && getNext() == null;
	}
}
