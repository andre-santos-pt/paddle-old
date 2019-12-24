package pt.iscte.paddle.model.impl;

import java.util.List;

import pt.iscte.paddle.interpreter.ExecutionError;
import pt.iscte.paddle.interpreter.ICallStack;
import pt.iscte.paddle.interpreter.IEvaluable;
import pt.iscte.paddle.interpreter.IExecutable;
import pt.iscte.paddle.interpreter.IReference;
import pt.iscte.paddle.interpreter.IStackFrame;
import pt.iscte.paddle.interpreter.IValue;
import pt.iscte.paddle.model.IArrayElement;
import pt.iscte.paddle.model.IArrayLength;
import pt.iscte.paddle.model.IExpression;
import pt.iscte.paddle.model.IProcedure;
import pt.iscte.paddle.model.IProgramElement;
import pt.iscte.paddle.model.IRecordFieldExpression;
import pt.iscte.paddle.model.IType;
import pt.iscte.paddle.model.IVariable;
import pt.iscte.paddle.model.IVariableAddress;
import pt.iscte.paddle.model.IVariableDereference;

class Variable extends Expression implements IVariable, IEvaluable, IExecutable {

	private final IProgramElement parent;
	private final IType type;

	public Variable(IProgramElement parent, IType type, String...flags) {
		super(flags);
		this.parent = parent;
		this.type = type;
	}

	@Override
	public IProgramElement getParent() {
		return parent;
	}

	@Override
	public IType getType() {
		return type;
	}

	@Override
	public String toString() {
		return getId();
	}
	
	@Override
	public IVariableAddress address() {
		return new VariableAddress(this);
	}

	@Override
	public IVariableDereference dereference() {
		return new VariableDereference(this);
	}


	@Override
	public IRecordFieldExpression field(IVariable field) {
		return new RecordFieldExpression(this, field);
	}
	
//	@Override
//	public IVariable fieldVariable(IVariable field) {
//		return new RecordFieldVariable(this, field);
//	}

	@Override
	public IArrayLength length(List<IExpression> indexes) {
		return new ArrayLength(this, indexes);
	}

	@Override
	public IArrayElement element(List<IExpression> indexes) {
		return new ArrayElement(this, indexes);
	}

	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		IStackFrame topFrame = stack.getTopFrame();
		IReference ref = topFrame.getVariableStore(this);
		return type.isReference() ? ref : ref.getTarget();
	}

	@Override
	public void execute(ICallStack stack, List<IValue> expressions) throws ExecutionError {

	}
	
//	@Override
//	public String getId() {
//		String id = super.getId();
//		if(id == null) {
//			IProcedure procedure = getOwnerProcedure();
//			if(procedure != null)
//				id = "$" + procedure.getVariables().indexOf(this);
//			else if(isRecordField())
//				id = "$" + ((RecordType) parent).getFields().indexOf(this);
//		}
//		return id;
//	}
}
