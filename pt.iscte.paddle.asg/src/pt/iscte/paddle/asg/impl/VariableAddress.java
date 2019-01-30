package pt.iscte.paddle.asg.impl;

import java.util.List;

import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAddress;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IReference;
import pt.iscte.paddle.machine.IValue;

public class VariableAddress extends Expression implements IVariableAddress {

	private final IVariable variable;
	private final IDataType type;
	
	public VariableAddress(IVariable variable) {
//		super(variable.getParent(), variable.getId(), variable.getType());
		assert variable != null;
		this.variable = variable;
		type = new ReferenceType(variable.getType());
	}
	
//	@Override
//	public IVariableExpression getVariableExpression() {
//		return new VariableExpression(this);
//	}

	@Override
	public IVariable getVariable() {
		return variable;
	}
	
	@Override
	public IDataType getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "&" + variable.getId();
	}

	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		IReference reference = stack.getTopFrame().getVariableStore(variable.getId());
		return reference;
	}
}
