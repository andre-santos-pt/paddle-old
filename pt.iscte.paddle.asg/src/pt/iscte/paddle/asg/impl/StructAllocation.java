package pt.iscte.paddle.asg.impl;

import java.util.List;

import pt.iscte.paddle.asg.IStructAllocation;
import pt.iscte.paddle.asg.IStructType;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IValue;

class StructAllocation extends Expression implements IStructAllocation {

	private final IStructType type;
	
	public StructAllocation(IStructType type) {
		this.type = type;
	}


	@Override
	public IStructType getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "new " + getType().getId(); 
	}

	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) {
		return stack.getTopFrame().allocateObject(getType());
	}
}
