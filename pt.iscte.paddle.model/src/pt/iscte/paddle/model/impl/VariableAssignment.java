package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IReference;
import pt.iscte.paddle.interpreter.IStackFrame;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IVariable;
import pt.iscte.paddle.model.IVariableAssignment;

class VariableAssignment extends Statement implements IVariableAssignment {

	private final IVariable variable;
	private final IExpression expression;
	
	public VariableAssignment(IBlock parent, IVariable variable, IExpression expression, int index, String...flags) {
		super(parent, true, index, flags);
		this.variable = variable;
		this.expression = expression;
	}
	
	@Override
	public IVariable getTarget() {
		return variable;
	}

	@Override
	public IExpression getExpression() {
		return expression;
	}
	
	@Override
	public String toString() {
		return (variable == null ? "NULL" : variable.getId()) + " = " + expression;
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
