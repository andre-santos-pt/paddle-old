package pt.iscte.paddle.asg.impl;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IControlStructure;

abstract class ControlStructure extends ProgramElement implements IControlStructure {
	private final IBlock parent;
	
	public ControlStructure(IBlock parent) {
		this.parent = parent;
		if(parent != null)
			((Block) parent).addInstruction(this);
	}
	
	@Override
	public IBlock getParent() {
		return parent;
	}

}
