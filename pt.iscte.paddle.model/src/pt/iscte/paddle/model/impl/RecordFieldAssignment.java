package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IRecord;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IRecordFieldAssignment;
import pt.iscte.paddle.model.IRecordFieldExpression;
import pt.iscte.paddle.model.IReferenceType;
import pt.iscte.paddle.model.IVariable;

class RecordFieldAssignment extends Statement implements IRecordFieldAssignment {
	private final IRecordFieldExpression target;
	private final IExpression expression;
	
	public RecordFieldAssignment(IBlock parent, IRecordFieldExpression target, IExpression expression, int index) {
		super(parent);
		this.target = target;
		this.expression = expression;
		addToParent(index);
	}
	
	@Override
	public IRecordFieldExpression getTarget() {
		return target;
	}
	
	@Override
	public IVariable getField() {
		return target.getField();
	}	

	@Override
	public IExpression getExpression() {
		return expression;
	}
	
	@Override
	public String toString() {
		IExpression target = getTarget();
		IVariable field = getField();
		if(target.getType() instanceof IReferenceType)
			return target.toString() + " = " + expression;
		else
			return target.toString() + " = " + expression;
	}
	
	@Override
	public void execute(ICallStack stack, List<IValue> expressions) throws ExecutionError {
		IRecord r = target.resolveTarget(stack);
		r.setField(target.getField(), expressions.get(0));
	}

}