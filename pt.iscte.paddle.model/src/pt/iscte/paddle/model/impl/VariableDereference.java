package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IReference;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.IVariableDereference;
import pt.iscte.paddle.model.IVariableExpression;

public class VariableDereference extends Expression implements IVariableDereference {
	private final IVariableExpression variable;
	
	public VariableDereference(IVariableExpression variable) {
		assert variable != null;
		this.variable = variable;
	}

	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		IVariableDeclaration v = resolve().getVariable();
		IReference reference = stack.getTopFrame().getVariableStore(v);
		IVariableExpression var = variable;
		while(var instanceof VariableDereference) {		
			var = ((VariableDereference) var).variable;
			reference = (IReference) reference.getTarget();
		}
		return reference;
	}

	@Override
	public IVariableExpression getTarget() {
		return variable;
	}

	private IVariableExpression resolve() {
		IVariableExpression var = variable;
		while(var instanceof VariableDereference)
			var = ((VariableDereference) var).variable;
		return var;
	}
	
	@Override
	public String toString() {
		return getId();
	}

	@Override
	public IType getType() {
		return variable.getType();
	}

	@Override
	public String getId() {
		return variable.getId();
	}
}
