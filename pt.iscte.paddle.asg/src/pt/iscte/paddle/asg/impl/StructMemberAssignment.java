package pt.iscte.paddle.asg.impl;

import java.util.List;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IStructMemberAssignment;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IStructObject;
import pt.iscte.paddle.machine.IValue;

class StructMemberAssignment extends Statement implements IStructMemberAssignment {
	
	private final IVariable variable;
	private final String memberId;
	private final IExpression expression;
	
	public StructMemberAssignment(IBlock parent, IVariable variable, String memberId, IExpression expression) {
		super(parent, true);
		// TODO assert type
		this.variable = variable;
		this.memberId = memberId;
		this.expression = expression;
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
	public IExpression getExpression() {
		return expression;
	}
	
	@Override
	public boolean onTarget() {
		return false;
	}

	@Override
	public String toString() {
		return variable.getId() + "." + memberId + " = " + expression;
	}
	
	@Override
	public boolean execute(ICallStack stack, List<IValue> expressions) throws ExecutionError {
		IStructObject object = (IStructObject) stack.getTopFrame().getVariableStore(getVariable().getId());
		object.setField(getMemberId(), expressions.get(0));
		return true;
	}
}
