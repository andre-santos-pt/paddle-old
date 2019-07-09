package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IRecord;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IRecordFieldAssignment;
import pt.iscte.paddle.model.IReferenceType;
import pt.iscte.paddle.model.IVariable;

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
		if(variable.getType() instanceof IReferenceType)
			return variable.getId() + "->" + field.getId() + " = " + expression;
		else
			return variable.getId() + "." + field.getId() + " = " + expression;
	}
	
	@Override
	public void execute(ICallStack stack, List<IValue> expressions) throws ExecutionError {
		IRecord object = (IRecord) stack.getTopFrame().getVariableStore(getVariable()).getTarget();
		object.setField(field, expressions.get(0));
	}
}
