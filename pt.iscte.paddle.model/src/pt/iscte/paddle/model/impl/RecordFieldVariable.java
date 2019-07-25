package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IRecord;
import pt.iscte.paddle.interpreter.IReference;
import pt.iscte.paddle.interpreter.IStackFrame;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.IRecordFieldVariable;
import pt.iscte.paddle.model.IVariable;

public class RecordFieldVariable extends Variable implements IRecordFieldVariable {
	private final IVariable field;

	public RecordFieldVariable(IVariable parent, IVariable field) {
		super(parent, field.getType());
		this.field = field;
	}

	@Override
	public IVariable getField() {
		return field;
	}

	@Override
	public String getId() {
		// TODO recursive id
		return getParent().getId() + "." + field.getId();
	}

	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		// TODO recursive field access?
		
		IStackFrame topFrame = stack.getTopFrame();
		IReference ref = topFrame.getVariableStore((IVariable) getParent());
		IRecord rec = (IRecord) ref.getTarget();
		ref = rec.getField(field);
		return getType().isReference() ? ref : ref.getTarget();
	}
}
