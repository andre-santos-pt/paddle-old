package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IReference;
import pt.iscte.paddle.interpreter.IStackFrame;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IVariableAssignment;
import pt.iscte.paddle.model.IVariableDeclaration;

class VariableAssignment extends Statement implements IVariableAssignment {

	private final IVariableDeclaration variable;
	private final IExpression expression;
	
	public VariableAssignment(IBlock parent, IVariableDeclaration variable, IExpression expression, int index, String...flags) {
		super(parent, flags);
		this.variable = variable;
		this.expression = expression;
		addToParent(index);
	}
	
	@Override
	public IVariableDeclaration getTarget() {
		return variable;
	}

	@Override
	public IExpression getExpression() {
		return expression;
	}
	

	@Override
	public void execute(ICallStack stack, List<IValue> expressions) throws ExecutionError {
		assert expressions.size() == 1;	
		IValue newValue = expressions.get(0);
		IStackFrame topFrame = stack.getTopFrame();
		IReference ref = topFrame.getVariableStore(variable);
		ref.setTarget(newValue);
	}
	
	@Override
	public String getExplanation(ICallStack stack, List<IValue> expressions) {
		return "Modifies variable " + getTarget().getId() + " to value " + expressions.get(0);
	}
}
