package pt.iscte.paddle.asg.impl;

import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.ILoop;

class Loop extends ControlStructure implements ILoop {
	public Loop(Block parent, IExpression guard) {
		super(parent, guard);
	}
	
	@Override
	public String toString() {
		return "while(" + getGuard() + ") " + getBlock();
	}
}
