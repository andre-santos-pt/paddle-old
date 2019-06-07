package pt.iscte.paddle.asg.impl;

import java.util.List;

import pt.iscte.paddle.asg.IArrayElement;
import pt.iscte.paddle.asg.IArrayLength;
import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IProcedure;
import pt.iscte.paddle.asg.IProgramElement;
import pt.iscte.paddle.asg.IRecordFieldExpression;
import pt.iscte.paddle.asg.IRecordFieldVariable;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAddress;
import pt.iscte.paddle.asg.IVariableDereference;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IEvaluable;
import pt.iscte.paddle.machine.IExecutable;
import pt.iscte.paddle.machine.IReference;
import pt.iscte.paddle.machine.IStackFrame;
import pt.iscte.paddle.machine.IValue;

class Variable extends Expression implements IVariable, IEvaluable, IExecutable {

	private final IProgramElement parent;
	private final IType type;

	public Variable(IProgramElement parent, IType type) {
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
	
	@Override
	public IRecordFieldVariable fieldVariable(IVariable field) {
		return new RecordFieldVariable(this, field);
	}

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
	
	@Override
	public String getId() {
		String id = super.getId();
		if(id == null) {
			IProcedure procedure = getProcedure();
			if(procedure != null)
				id = "$" + procedure.getVariables().indexOf(this);
			else if(isRecordField())
				id = "$" + ((RecordType) parent).getFields().indexOf(this);
		}
		return id;
	}
}
