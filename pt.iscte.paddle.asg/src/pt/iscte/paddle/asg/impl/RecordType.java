package pt.iscte.paddle.asg.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.asg.IReferenceType;
import pt.iscte.paddle.asg.IRecordAllocation;
import pt.iscte.paddle.asg.IRecordType;
import pt.iscte.paddle.asg.IVariable;

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
	public IVariable addMemberVariable(IType type) {
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
	public IRecordAllocation allocationExpression() {
		return new RecordAllocation(this);
	}
	
	@Override
	public IReferenceType reference() {
		return new ReferenceType(this);
	}
}
