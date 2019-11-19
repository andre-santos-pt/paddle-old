package pt.iscte.paddle.model.impl;

import pt.iscte.paddle.interpreter.IExecutable;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IStatement;

abstract class Statement extends ProgramElement implements IStatement, IExecutable {
	private final IBlock parent;

	public Statement(IBlock parent, boolean addToParent, int index) {
		this.parent = parent;
		if(parent != null && addToParent) {
			Block block = (Block) parent;
			block.addChild(this, index);
		}
	}

	@Override
	public IBlock getParent() {
		return parent;
	}
	
	public void remove() {
		assert parent != null;
		((Block) parent).remove(this);
	}
}
