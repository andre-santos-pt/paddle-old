package pt.iscte.paddle.model.cfg.impl;

import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.cfg.IBranchNode;
import pt.iscte.paddle.model.cfg.INode;

public class BranchNode extends Node implements IBranchNode {

	private final IExpression expression;
	private INode alternative;
	
	BranchNode(IExpression expression) {
		assert expression != null;
		this.expression = expression;
	}
	
	@Override
	public INode getAlternative() {
		return alternative;
	}
	
	@Override
	public void setBranch(INode node) {
		assert alternative == null;
		alternative = node;
	}

	@Override
	public IExpression getElement() {
		return expression;
	}
	
	@Override
	public String toString() {
		INode next = getNext();
		if(next == null)
			return expression + "\t\t (no next!)";
		else
			return expression + " -> " + (!next.isExit() ? next.getElement().toString() : next.toString());
	}

}
