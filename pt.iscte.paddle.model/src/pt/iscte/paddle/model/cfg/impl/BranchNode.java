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
	public boolean hasBranch() {
		return alternative != null;
	}
	
	@Override
	public String toString() {
		INode next = getNext();
		String s = "";
		String nextText = "(NO NEXT!)";
		if(next != null)
			nextText = next.isExit() ? next.toString() : next.getElement().toString();
			
		s += expression + " >>>> " + nextText + "\n";
		
		String altText = "(NO BRANCH!)";
		if(alternative != null)
			altText = alternative.isExit() ? alternative.toString() : alternative.getElement().toString();
		s += expression + " >..> " + altText;

		return s;
	}
	
	@Override
	public boolean isEquivalentTo(INode node) {
		IBranchNode n = (IBranchNode) node; // checked by super
		return super.isEquivalentTo(node) &&
				(
				getAlternative() == null && n.getAlternative() == null ||
				getAlternative() != null && getAlternative().getElement() == null && n.getAlternative().getElement() == null ||
				getAlternative() != null && getAlternative().getElement() != null && getAlternative().getElement().equals(n.getAlternative().getElement())
				);
	}

}
