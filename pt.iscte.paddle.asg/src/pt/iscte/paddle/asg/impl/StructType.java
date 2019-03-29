package pt.iscte.paddle.asg.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IReferenceType;
import pt.iscte.paddle.asg.IRecordAllocation;
import pt.iscte.paddle.asg.IRecordType;
import pt.iscte.paddle.asg.IVariable;

class StructType extends ProgramElement implements IRecordType {
	private final List<IVariable> variables;
	
	StructType() {
		this.variables = new ArrayList<>(5);
	}
	
	@Override
	public List<IVariable> getMemberVariables() {
		return Collections.unmodifiableList(variables);
	}
	
	@Override
	public IVariable addMemberVariable(IDataType type) {
		IVariable var = new Variable(this, type);
		variables.add(var);
		return var;
	}
	
	@Override
	public String toString() {
		String text = "struct " + getId() + " {";
		for (IVariable member : variables) {
			text += member.getDeclaration() + ";";
		}
		return text + "}";
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
		return new StructAllocation(this);
	}
	
	@Override
	public IReferenceType reference() {
		return new ReferenceType(this);
	}
}
