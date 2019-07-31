package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IRecord;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IArrayElement;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IRecordFieldExpression;
import pt.iscte.paddle.model.IReferenceType;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariable;

class RecordFieldExpression extends Expression implements IRecordFieldExpression {

	private final IExpression target;
	private final IVariable field;
	
	public RecordFieldExpression(IExpression target, IVariable field) {
		assert target != null;
		assert field != null;
		this.target = target;
		this.field = field;
	}

	@Override
	public IExpression getTarget() {
		return target;
	}

	@Override
	public IVariable getField() {
		return field;
	}

	@Override
	public IType getType() {
		return field.getType();
	}
	
	@Override
	public String toString() {
		if(target.getType() instanceof IReferenceType)
			return target.getId() + "->" + field.getId();
		else
			return target.getId() + "." + field.getId();
	}
	
	@Override
	public IRecordFieldExpression field(IVariable field) {
		return new RecordFieldExpression(this, field);
	}

	@Override
	public IArrayElement element(List<IExpression> indexes) {
		return new ArrayElement(this, indexes);
	}
	
	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		assert target instanceof IVariable;
		IRecord r = resolveTarget(stack); 
		IValue field = r.getField(this.field).getTarget();
		return field;
	}
	
}
