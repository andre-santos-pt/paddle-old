package pt.iscte.paddle.model.impl;

import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IBlockElement;
import pt.iscte.paddle.model.IProgramElement;

public abstract class BlockChild extends ProgramElement implements IBlockElement {
	private final IBlock parent;
	
	BlockChild(IBlock parent) {
		this.parent = parent;
	}
	
	@Override
	public IProgramElement getParent() {
		return parent;
	}

}
