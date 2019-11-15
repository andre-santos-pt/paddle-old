package pt.iscte.paddle.model.impl;

import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.ISelection;

class Selection extends ControlStructure implements ISelection {
	private final IBlock alternativeBlock;

	public Selection(Block parent, IExpression guard, boolean hasAlternative) {
		super(parent, guard);
		alternativeBlock = hasAlternative ? parent.addLooseBlock(this) : null;
	}

	@Override
	public String toString() {
		String code = "if(" + getGuard() + ") " + getBlock();
		if(alternativeBlock != null) {
			String tabs = "";
			int d = alternativeBlock.getDepth();
			for(int i = 1; i < d; i++)
				tabs += "\t";
			code += tabs + "else " + alternativeBlock;
		}
		return code;
	}

	@Override
	public IBlock getAlternativeBlock() {
		return alternativeBlock;
	}
}
