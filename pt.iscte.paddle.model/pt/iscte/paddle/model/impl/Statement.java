package pt.iscte.paddle.model.impl;

import pt.iscte.paddle.interpreter.IExecutable;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IStatement;

abstract class Statement extends ProgramElement implements IStatement, IExecutable {
	private final IBlock parent;

	public Statement(IBlock parent, String ... flags) {
		super(flags);
		this.parent = parent;
//		if(parent != null) {
//			Block block = (Block) parent;
//			block.addChild(this, index);
//		}
	}
	
	void addToParent(int index) {
		if(parent != null) {
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
