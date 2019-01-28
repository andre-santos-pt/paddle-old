package pt.iscte.paddle.asg.impl;

import java.util.List;

import com.google.common.collect.ImmutableList;

import pt.iscte.paddle.asg.IDataType;
import pt.iscte.paddle.asg.IExpression;
import pt.iscte.paddle.asg.IVariableAddress;
import pt.iscte.paddle.asg.IVariableExpression;
import pt.iscte.paddle.machine.ExecutionError;
import pt.iscte.paddle.machine.ICallStack;
import pt.iscte.paddle.machine.IReference;
import pt.iscte.paddle.machine.IValue;

public class VariableAddress extends Expression implements IVariableAddress {

	private final IVariableExpression exp;
	private final IDataType type;
	
	public VariableAddress(IVariableExpression exp) {
		this.exp = exp;
		type = new ReferenceType(exp.getType());
	}
	
	@Override
	public IVariableExpression getVariableExpression() {
		return exp;
	}

	@Override
	public IDataType getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "&" + exp.toString();
	}

	@Override
	public boolean isDecomposable() {
		return false;
	}

	@Override
	public List<IExpression> decompose() {
		return ImmutableList.of();
	}

	@Override
	public IValue evalutate(List<IValue> values, ICallStack stack) throws ExecutionError {
		IReference reference = stack.getTopFrame().getVariableStore(exp.getVariable().getId());
		return reference;
	}
}
