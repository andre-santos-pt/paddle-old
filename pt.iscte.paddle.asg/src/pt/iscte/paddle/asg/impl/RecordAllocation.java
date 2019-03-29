package pt.iscte.paddle.asg.impl;

import java.util.List;

import pt.iscte.paddle.asg.IRecordAllocation;
import pt.iscte.paddle.asg.IRecordType;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IValue;

class RecordAllocation extends Expression implements IRecordAllocation {

	private final IRecordType type;
	
	public RecordAllocation(IRecordType type) {
		this.type = type;
	}


	@Override
	public IRecordType getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "new " + getType().getId(); 
	}

	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) {
		return stack.getTopFrame().allocateRecord(getType());
	}
}
