package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IRecordAllocation;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.IType;

class RecordAllocation extends Expression implements IRecordAllocation {

	private final IRecordType type;
	
	public RecordAllocation(IRecordType type) {
		this.type = type;
	}


	@Override
	public IType getType() {
		return type.reference();
	}
	
	@Override
	public String toString() {
		return "new " + type.getId(); 
	}

	@Override
	public IRecordType getRecordType() {
		return type;
	}
	
	
	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) {
		return stack.getProgramState().allocateRecord(type);
	}
}
