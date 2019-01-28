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
	private final boolean onTarget;
	
	private VariableAssignment(IBlock parent, IVariable variable, IExpression expression, boolean onTarget) {
		super(parent, true);
		if(variable.getProcedure() != parent.getProcedure())
			throw new RuntimeException("Violation: variable to assign must be within the same procedure");
		
		this.variable = variable;
		this.expression = expression;
		this.onTarget = onTarget;
	}
	
	public VariableAssignment(IBlock parent, IVariable variable, IExpression expression) {
		this(parent, variable, expression, false);
	}
	
	public static VariableAssignment onTarget(IBlock parent, IVariable variable, IExpression expression) {
		return new VariableAssignment(parent, variable, expression, true);
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
	public boolean onTarget() {
		return onTarget;
	}
	
	@Override
	public String toString() {
		return (onTarget ? "*" : "") + variable.getId() + " = " + expression;
	}

	@Override
	public boolean execute(ICallStack stack, List<IValue> expressions) throws ExecutionError {
		assert expressions.size() == 1;
		// TODO validate 
		if(!variable.isPointer() && onTarget)
			System.err.println("incompatible");
		
		IValue newValue = expressions.get(0);
		IStackFrame topFrame = stack.getTopFrame();
		String varId = getVariable().getId();
		IReference ref = topFrame.getVariableStore(varId);
		if(variable.isPointer() && onTarget)
			ref.setValue(newValue);
		else
			ref.setTarget(newValue);
		return true;
	}
}
