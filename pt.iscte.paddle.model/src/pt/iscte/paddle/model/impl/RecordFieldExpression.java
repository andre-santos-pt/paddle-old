package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IRecord;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IRecordFieldExpression;
import pt.iscte.paddle.model.IReferenceType;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariable;

class RecordFieldExpression extends Expression implements IRecordFieldExpression {

	private final IVariable variable;
	private final IVariable field;
	
	public RecordFieldExpression(IVariable variable, IVariable field) {
		assert variable != null;
		assert field != null;
		this.variable = variable;
		this.field = field;
	}

	@Override
	public IVariable getVariable() {
		return variable;
	}

	@Override
	public IVariable getField() {
		return field;
	}

	@Override
	public IType getType() {
		return field.getType();
	}
	
	@Override
	public String toString() {
		if(variable.getType() instanceof IReferenceType)
			return variable.getId() + "->" + field.getId();
		else
			return variable.getId() + "." + field.getId();
	}
	
	
	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) {
		IRecord object = (IRecord) stack.getTopFrame().getVariableStore(variable).getTarget();
		IValue field = object.getField(this.field).getTarget();
		return field;
	}
}
