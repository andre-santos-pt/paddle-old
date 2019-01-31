package pt.iscte.paddle.asg.impl;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.ISelection;

class Selection extends ControlStructure implements ISelection {
	private final IBlock alternativeBlock;

	public Selection(Block parent, IExpression guard, boolean hasAlternative) {
		super(parent, guard);
		alternativeBlock = hasAlternative ? parent.addLooseBlock() : null;
	}

	@Override
	public String toString() {
		return "if " + getGuard() + " " + super.toString();
	}

	@Override
	public IBlock getAlternativeBlock() {
		return alternativeBlock;
	}
}
