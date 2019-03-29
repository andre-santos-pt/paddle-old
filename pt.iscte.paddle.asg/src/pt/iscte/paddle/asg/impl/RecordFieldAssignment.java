package pt.iscte.paddle.asg.impl;

import java.util.List;

import pt.iscte.paddle.asg.IBlock;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IRecordFieldAssignment;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IRecord;
import pt.iscte.paddle.machine.IValue;

class RecordFieldAssignment extends Statement implements IRecordFieldAssignment {
	private final IVariable variable;
	private final IVariable field;
	private final IExpression expression;
	
	public RecordFieldAssignment(IBlock parent, IVariable variable, IVariable field, IExpression expression) {
		super(parent, true);
		this.variable = variable;
		this.field = field;
		this.expression = expression;
	}

	@Override
	public IVariable getVariable() {
		return variable;
	}
	
	@Override
	public IVariable getField() {
		return field;
	}	

	@Override
	public IExpression getExpression() {
		return expression;
	}
	
	@Override
	public String toString() {
		return variable.getId() + "." + field.getId() + " = " + expression;
	}
	
	@Override
	public void execute(ICallStack stack, List<IValue> expressions) throws ExecutionError {
		IRecord object = (IRecord) stack.getTopFrame().getVariableStore(getVariable()).getTarget();
		object.setField(field, expressions.get(0));
	}
}
