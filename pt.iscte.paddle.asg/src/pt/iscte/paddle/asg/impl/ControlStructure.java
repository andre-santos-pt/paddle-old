package pt.iscte.paddle.asg.impl;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IControlStructure;
import pt.iscte.paddle.asg.IExpression;

abstract class ControlStructure extends ProgramElement implements IControlStructure {
	private final IBlock parent;
	private final IBlock block;
	private final IExpression guard;

	public ControlStructure(Block parent, IExpression guard) {
		assert parent != null;
		assert guard != null;
		
		this.parent = parent;
		parent.addChild(this);

		this.guard = guard;
		this.block = parent.addLooseBlock();
	}
	
	@Override
	public IBlock getParent() {
		return parent;
	}
	
	@Override
	public IBlock getBlock() {
		return block;
	}
	
	@Override
	public IExpression getGuard() {
		return guard;
	}

}
