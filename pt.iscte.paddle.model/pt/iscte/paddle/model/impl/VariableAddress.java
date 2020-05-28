package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IReference;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariableAddress;
import pt.iscte.paddle.model.IVariableExpression;

public class VariableAddress extends Expression implements IVariableAddress {

	private final IVariableExpression variable;
	private final IType type;
	
	public VariableAddress(IVariableExpression variable) {
		assert variable != null;
		this.variable = variable;
		type = new ReferenceType(variable.getType());
	}
	
	@Override
	public IVariableExpression getTarget() {
		return variable;
	}
	
	@Override
	public IType getType() {
		return type;
	}
	
	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		IReference reference = stack.getTopFrame().getVariableStore(variable.getVariable());
		return reference;
	}
}
