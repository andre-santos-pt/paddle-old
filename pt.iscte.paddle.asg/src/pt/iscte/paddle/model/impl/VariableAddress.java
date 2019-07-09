package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IReference;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariable;
import pt.iscte.paddle.model.IVariableAddress;

public class VariableAddress extends Expression implements IVariableAddress {

	private final IVariable variable;
	private final IType type;
	
	public VariableAddress(IVariable variable) {
		assert variable != null;
		this.variable = variable;
		type = new ReferenceType(variable.getType());
	}
	
	@Override
	public IVariable getVariable() {
		return variable;
	}
	
	@Override
	public IType getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "&" + variable.getId();
	}

	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		IReference reference = stack.getTopFrame().getVariableStore(variable);
		return reference;
	}
}
