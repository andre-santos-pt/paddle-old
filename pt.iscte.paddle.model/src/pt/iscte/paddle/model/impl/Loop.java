package pt.iscte.paddle.model.impl;

import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.ILoop;

class Loop extends ControlStructure implements ILoop {
	public Loop(Block parent, IExpression guard, int index) {
		super(parent, guard, index);
	}
	
	@Override
	public String toString() {
		return "while(" + getGuard() + ") " + getBlock();
	}
}
