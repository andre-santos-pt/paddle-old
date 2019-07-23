package pt.iscte.paddle.model.impl;

import pt.iscte.paddle.interpreter.IExecutable;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IStatement;

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
