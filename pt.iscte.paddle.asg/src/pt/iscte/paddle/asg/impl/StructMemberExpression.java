package pt.iscte.paddle.asg.impl;

import java.util.List;

import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IStructMemberExpression;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IStructObject;
import pt.iscte.paddle.machine.IValue;

class StructMemberExpression extends Expression implements IStructMemberExpression {

	private final IVariable variable;
	private final String memberId;
	
	public StructMemberExpression(IVariable variable, String memberId) {
		assert variable != null;
		assert memberId != null;
		
		// TODO validation variable
		this.variable = variable;
		this.memberId = memberId;
	}

	@Override
	public IVariable getVariable() {
		return variable;
	}

	@Override
	public String getMemberId() {
		return memberId;
	}

	@Override
	public IDataType getType() {
		return variable.getType();
	}
	
	@Override
	public String toString() {
		return variable.getId() + "." + memberId;
	}
	
	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) {
		// TODO validate
		IStructObject object = (IStructObject) stack.getTopFrame().getVariableStore(getVariable().getId());
		IValue field = object.getField(getMemberId());
		return field;
	}
}
