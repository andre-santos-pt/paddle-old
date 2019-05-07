package pt.iscte.paddle.asg.impl;

import java.util.List;

import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.asg.IRecordFieldExpression;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IRecord;
import pt.iscte.paddle.machine.IValue;

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
		return variable.getId() + "." + field.getId();
	}
	
	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) {
		IRecord object = (IRecord) stack.getTopFrame().getVariableStore(getVariable()).getTarget();
		IValue field = object.getField(this.field);
		return field;
	}
}
