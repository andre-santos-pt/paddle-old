package pt.iscte.paddle.model.impl;

import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IControlStructure;
import pt.iscte.paddle.model.IExpression;

abstract class ControlStructure extends ProgramElement implements IControlStructure {
	private final IBlock parent;
	private final IBlock block;
	private final IExpression guard;

	public ControlStructure(Block parent, IExpression guard, int index, String ... flags) {
		super(flags);
		assert parent != null;
		assert guard != null;
		
		this.parent = parent;
		this.guard = guard;
		this.block = parent.addLooseBlock(this);
		parent.addChild(this, index);
		guard.setProperty(IControlStructure.class, this);
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
