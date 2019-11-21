package pt.iscte.paddle.model.impl;

import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.ILoop;

class Loop extends ControlStructure implements ILoop {
	public Loop(Block parent, IExpression guard, int index, String ... flags) {
		super(parent, guard, index, flags);
	}
	
	@Override
	public String toString() {
		return "while(" + getGuard() + ") " + getBlock();
	}
}
