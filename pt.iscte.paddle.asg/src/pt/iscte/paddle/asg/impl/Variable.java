package pt.iscte.paddle.asg.impl;

import java.util.List;

import pt.iscte.paddle.asg.IArrayElement;
import pt.iscte.paddle.asg.IArrayLength;
import pt.iscte.paddle.asg.IType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IProgramElement;
import pt.iscte.paddle.asg.IRecordFieldExpression;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAddress;
import pt.iscte.paddle.asg.IVariableReferenceValue;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IEvaluable;
import pt.iscte.paddle.machine.IExecutable;
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
	public IVariableReferenceValue value() {
		return new VariableReferenceValue(this);
	}


	@Override
	public IRecordFieldExpression field(IVariable field) {
		return new RecordFieldExpression(this, field);
	}

	@Override
	public IArrayLength length(List<IExpression> indexes) {
		return new ArrayLengthExpression(this, indexes);
	}

	@Override
	public IArrayElement element(List<IExpression> indexes) {
		return new ArrayElementExpression(this, indexes);
	}

	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		IStackFrame topFrame = stack.getTopFrame();
		IValue val = topFrame.getVariableStore(this).getTarget();
		return val;
	}

	@Override
	public void execute(ICallStack stack, List<IValue> expressions) throws ExecutionError {

	}
}
