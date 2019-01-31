package pt.iscte.paddle.asg.impl;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IStatement;
import pt.iscte.paddle.machine.IExecutable;

abstract class Statement extends ProgramElement implements IStatement, IExecutable {
	private final IBlock parent;
	
	public Statement(IBlock parent, boolean addToParent) {
		this.parent = parent;
		if(parent != null && addToParent)
			((Block) parent).addChild(this);
	}
	
	@Override
	public IBlock getParent() {
		return parent;
	}

}
