package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IEvaluable;
import pt.iscte.paddle.interpreter.IReference;
import pt.iscte.paddle.interpreter.IStackFrame;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariableAddress;
import pt.iscte.paddle.model.IVariableDeclaration;
import pt.iscte.paddle.model.IVariableDereference;
import pt.iscte.paddle.model.IVariableExpression;

public class VariableExpression extends Expression implements IVariableExpression, IEvaluable {
	private final IVariableDeclaration variable;
	
	public VariableExpression(IVariableDeclaration variable) {
		this.variable = variable;
	}
	
	@Override
	public IVariableDeclaration getVariable() {
		return variable;
	}
	
	@Override
	public IType getType() {
		return variable.getType();
	}
	
	@Override
	public IVariableAddress address() {
		return new VariableAddress(this);
	}

	@Override
	public IVariableDereference dereference() {
		return new VariableDereference(this);
	}
	
	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		IStackFrame topFrame = stack.getTopFrame();
		IReference ref = topFrame.getVariableStore(variable);
		return variable.getType().isReference() ? ref : ref.getTarget();
	}

}
