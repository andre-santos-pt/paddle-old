package pt.iscte.paddle.asg.impl;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IBlockChild;
import pt.iscte.paddle.asg.IProgramElement;

public abstract class BlockChild extends ProgramElement implements IBlockChild {
	private final IBlock parent;
	
	BlockChild(IBlock parent) {
		this.parent = parent;
	}
	
	@Override
	public IProgramElement getParent() {
		return parent;
	}

}
