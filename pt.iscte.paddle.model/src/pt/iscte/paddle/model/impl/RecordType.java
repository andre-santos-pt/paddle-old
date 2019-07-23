package pt.iscte.paddle.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pt.iscte.paddle.model.IRecordAllocation;
import pt.iscte.paddle.model.IRecordType;
import pt.iscte.paddle.model.IReferenceType;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariable;

class RecordType extends ProgramElement implements IRecordType {
	private final List<IVariable> variables;
	
	RecordType() {
		this.variables = new ArrayList<>(5);
	}
	
	@Override
	public List<IVariable> getFields() {
		return Collections.unmodifiableList(variables);
	}
	
	@Override
	public IVariable addField(IType type) {
		IVariable var = new Variable(this, type);
		variables.add(var);
		return var;
	}
	
	@Override
	public String toString() {
		return getId();
	}
	
	@Override
	public int getMemoryBytes() {
		int bytes = 0;
		for(IVariable v : variables)
			bytes += v.getType().getMemoryBytes();
		return bytes;
	}
	
	@Override
	public IRecordAllocation heapAllocation() {
		return new RecordAllocation(this);
	}
	
	@Override
	public IReferenceType reference() {
		return new ReferenceType(this);
	}
}
