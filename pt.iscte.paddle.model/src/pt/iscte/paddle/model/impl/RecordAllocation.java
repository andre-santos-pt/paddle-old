package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IModel2CodeTranslator;
import pt.iscte.paddle.model.IRecordAllocation;
import pt.iscte.paddle.model.IRecordType;

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
