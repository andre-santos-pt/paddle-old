package pt.iscte.paddle.asg.impl;

import java.util.List;

import pt.iscte.paddle.asg.IArrayElementExpression;
import pt.iscte.paddle.asg.IArrayLengthExpression;
import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IProgramElement;
import pt.iscte.paddle.asg.IStructMemberExpression;
import pt.iscte.paddle.asg.IVariable;
import pt.iscte.paddle.asg.IVariableAddress;
import pt.iscte.paddle.asg.IVariableAssignment;
import pt.iscte.paddle.asg.IVariableReferenceValue;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IReference;
import pt.iscte.paddle.machine.IValue;

public class VariableReferenceValue extends Expression implements IVariableReferenceValue {
	private final IVariable variable;
	
	public VariableReferenceValue(IVariable variable) {
//		super(variable.getParent(), variable.getId(), variable.getType());
		assert variable != null;
		this.variable = variable;
	}

	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		IReference reference = stack.getTopFrame().getVariableStore(variable.getId());
		return reference.getTarget();
	}

	@Override
	public IVariable getVariable() {
		return variable;
	}

	//	@Override
//	public IVariableExpression getVariableExpression() {
//		return new VariableExpression(this);
//	}
	
	@Override
	public String toString() {
		return "*" + variable.getId();
	}

	@Override
	public IDataType getType() {
		return variable.getType();
	}

	@Override
	public IVariableAddress address() {
		return new VariableAddress(this);
	}

	@Override
	public IVariableReferenceValue valueOf() {
		return new VariableReferenceValue(this);
	}

	@Override
	public IArrayLengthExpression arrayLength(List<IExpression> indexes) {
		return variable.arrayLength(indexes);
	}

	@Override
	public IArrayElementExpression arrayElement(List<IExpression> indexes) {
		return variable.arrayElement(indexes);
	}

	@Override
	public IStructMemberExpression member(String memberId) {
		return variable.member(memberId);
	}

	@Override
	public IProgramElement getParent() {
		return variable.getParent();
	}

	@Override
	public String getId() {
		return variable.getId();
	}

	
}
