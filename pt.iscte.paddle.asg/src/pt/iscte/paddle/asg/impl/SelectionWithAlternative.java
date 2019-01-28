package pt.iscte.paddle.asg.impl;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.ISelectionWithAlternative;

class SelectionWithAlternative extends Selection implements ISelectionWithAlternative {
	private final IBlock alternativeBlock;
	
	public SelectionWithAlternative(Block parent, IExpression guard) {
		super(parent, guard);
		alternativeBlock = parent.addLooseBlock();
	}
	
	@Override
	public IBlock getAlternativeBlock() {
		return alternativeBlock;
	}
	
	@Override
	public String toString() {
		return super.toString() + " else " + alternativeBlock;
	}
}
