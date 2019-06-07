package pt.iscte.paddle.asg.impl;

import java.util.List;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IReference;
import pt.iscte.paddle.machine.IStackFrame;
import pt.iscte.paddle.machine.IValue;

class VariableAssignment extends Statement implements IVariableAssignment {

	private final IVariable variable;
	private final IExpression expression;
	
	public VariableAssignment(IBlock parent, IVariable variable, IExpression expression) {
		super(parent, true);
		
		// TODO to Types
//		if(variable.getProcedure() != parent.getProcedure())
//			throw new RuntimeException("Violation: variable to assign must be within the same procedure");
		
		this.variable = variable;
		this.expression = expression;
	}
	
	@Override
	public IVariable getVariable() {
		return variable;
	}

	@Override
	public IExpression getExpression() {
		return expression;
	}
	
	@Override
	public String toString() {
		return variable.getId() + " = " + expression;
	}

	@Override
	public void execute(ICallStack stack, List<IValue> expressions) throws ExecutionError {
		assert expressions.size() == 1;
		
		IValue newValue = expressions.get(0);
		IStackFrame topFrame = stack.getTopFrame();
		IReference ref = topFrame.getVariableStore(variable);
		ref.setTarget(newValue);
	}
}
