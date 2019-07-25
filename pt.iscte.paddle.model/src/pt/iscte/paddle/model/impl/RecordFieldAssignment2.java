package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IRecord;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IBlock;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IRecordFieldAssignment;
import pt.iscte.paddle.model.IRecordFieldVariable;
import pt.iscte.paddle.model.IReferenceType;
import pt.iscte.paddle.model.IVariable;

class RecordFieldAssignment2 extends Statement implements IRecordFieldAssignment {
//	private final IVariable variable;
//	private final IVariable field;
	private final IExpression expression;
	private final IRecordFieldVariable var;
	
	public RecordFieldAssignment2(IBlock parent, IRecordFieldVariable var, IExpression expression) {
		super(parent, true);
		this.var = var;
//		this.variable = null;
//		this.field = null;
		this.expression = expression;
	}

	@Override
	public IVariable getVariable() {
		return (IVariable) var.getParent();
	}
	
	@Override
	public IVariable getField() {
		return var.getField();
	}	

	@Override
	public IExpression getExpression() {
		return expression;
	}
	
	@Override
	public String toString() {
		IVariable variable = getVariable();
		IVariable field = getField();
		if(variable.getType() instanceof IReferenceType)
			return variable.getId() + "->" + field.getId() + " = " + expression;
		else
			return variable.getId() + "." + field.getId() + " = " + expression;
	}
	
	@Override
	public void execute(ICallStack stack, List<IValue> expressions) throws ExecutionError {
		// TODO recursive field access? ==> start from first ref
		
		IVariable f = var;
		IRecord r = (IRecord) stack.getTopFrame().getVariableStore((IVariable) f.getParent()).getTarget();;
		while(f.getParent() instanceof IRecordFieldVariable) {
			f = (IVariable) f.getParent();
			r = (IRecord) stack.getTopFrame().getVariableStore((IVariable) f.getParent()).getTarget();
		}
		
		r.setField(((IRecordFieldVariable) f).getField(), expressions.get(0));
	}
}
